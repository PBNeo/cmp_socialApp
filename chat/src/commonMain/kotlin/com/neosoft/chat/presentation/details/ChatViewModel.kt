package com.neosoft.chat.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.neosoft.chat.domain.entity.ChatMessage
import com.neosoft.chat.domain.usecase.ConnectWebSocketUseCase
import com.neosoft.chat.domain.usecase.DisconnectWebSocketUseCase
import com.neosoft.chat.domain.usecase.GetMessagesUseCase
import com.neosoft.chat.domain.usecase.ObserveMessagesUseCase
import com.neosoft.chat.domain.usecase.SendMessageUseCase
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

class ChatViewModel(
    private val connectWebSocketUseCase: ConnectWebSocketUseCase,
    private val disconnectWebSocketUseCase: DisconnectWebSocketUseCase,
    private val sendMessageUseCase: SendMessageUseCase,
    private val observeMessagesUseCase: ObserveMessagesUseCase,
    private val getMessagesUseCase: GetMessagesUseCase,
    private val meUserId: String
) : ViewModel() {

    private val _state = MutableStateFlow(ChatState(isLoading = true))
    val state: StateFlow<ChatState> = _state

    private var incomingCollectorJob: Job? = null
    private var typingJob: Job? = null
    private val typingUsersMutable = mutableSetOf<String>()

    fun loadHistory(chatId: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)
            try {
                val history = getMessagesUseCase(chatId)
                _state.value = _state.value.copy(isLoading = false, messages = history)
            } catch (e: Exception) {
                _state.value = _state.value.copy(isLoading = false, error = e.message)
            }
        }
    }

    fun connect(authToken: String? = null) {
        viewModelScope.launch {
            try {
                connectWebSocketUseCase(authToken)
                _state.value = _state.value.copy(isConnected = true)
                // collect incoming messages
                incomingCollectorJob?.cancel()
                incomingCollectorJob = launch {
                    observeMessagesUseCase().collect { msg ->
                        // Append message
                        _state.value = _state.value.copy(messages = _state.value.messages + msg)
                        // If message is typing/special, handle accordingly (depends on your envelope types)
                    }
                }
            } catch (e: Exception) {
                _state.value = _state.value.copy(error = e.message)
            }
        }
    }

    fun disconnect() {
        viewModelScope.launch {
            try {
                incomingCollectorJob?.cancel()
                disconnectWebSocketUseCase()
                _state.value = _state.value.copy(isConnected = false)
            } catch (e: Exception) {
                _state.value = _state.value.copy(error = e.message)
            }
        }
    }

    fun sendText(chatId: String, text: String) {
        if (text.isBlank()) return
        val message = ChatMessage(
            id = generateId(),
            chatId = chatId,
            from = meUserId,
            to = "", // if you use direct chat put receiver id here
            content = text,
            timestamp = 1,
            type = "text"
        )

        viewModelScope.launch {
            try {
                // optimistic update
                _state.value = _state.value.copy(messages = _state.value.messages + message)
                sendMessageUseCase(message)
            } catch (e: Exception) {
                _state.value = _state.value.copy(error = e.message)
            }
        }
    }

    fun onAction(action: ChatAction, chatId: String) {
        when (action) {
            is ChatAction.SendText -> sendText(chatId, action.text)
            is ChatAction.SendImage -> sendImage(chatId, action.localUri)
            ChatAction.Connect -> connect(null)
            ChatAction.Disconnect -> disconnect()
            is ChatAction.Typing -> sendTypingEvent(action.isTyping)
        }
    }

    @OptIn(ExperimentalTime::class)
    private fun sendImage(chatId: String, localUri: String) {
        // image upload flow: 1) upload to server (REST multipart) -> get url
        // 2) send message via websocket with messageType=image and payload=imageUrl
        viewModelScope.launch {
            try {
                // TODO: implement upload and then send message. For now produce a placeholder message
                val message = ChatMessage(
                    id = generateId(),
                    chatId = chatId,
                    from = meUserId,
                    to = "",
                    content = "[image:$localUri]",
                    timestamp = Clock.System.now().toEpochMilliseconds(),
                    type = "image"
                )
                _state.value = _state.value.copy(messages = _state.value.messages + message)
                sendMessageUseCase(message)
            } catch (e: Exception) {
                _state.value = _state.value.copy(error = e.message)
            }
        }
    }

    private fun sendTypingEvent(isTyping: Boolean) {
        // Optionally send typing envelope over WS. Implementation depends on server protocol.
        // Locally show typing status for demo.
        if (isTyping) {
            typingJob?.cancel()
            // add me as typing user (or handle remote typing separately)
            typingUsersMutable.add(meUserId)
            _state.value = _state.value.copy(typingUsers = typingUsersMutable.toList())
            typingJob = viewModelScope.launch {
                delay(3000) // clear typing after 3s of inactivity
                typingUsersMutable.remove(meUserId)
                _state.value = _state.value.copy(typingUsers = typingUsersMutable.toList())
            }
        } else {
            typingUsersMutable.remove(meUserId)
            _state.value = _state.value.copy(typingUsers = typingUsersMutable.toList())
        }
    }

    @OptIn(ExperimentalTime::class)
    private fun generateId(): String = "local_${Clock.System.now().toEpochMilliseconds()}"
}