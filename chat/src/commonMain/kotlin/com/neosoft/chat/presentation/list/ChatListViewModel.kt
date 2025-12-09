package com.neosoft.chat.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.neosoft.chat.domain.usecase.GetChatsUseCase

class ChatListViewModel(
    private val getChatsUseCase: GetChatsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ChatListState(isLoading = true))
    val state: StateFlow<ChatListState> = _state

    init {
        loadChats()
    }

    /** Loads chat list from domain layer */
    private fun loadChats() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)

            try {
                val chats = getChatsUseCase()
                _state.value = ChatListState(
                    isLoading = false,
                    chats = chats,
                    error = null
                )
            } catch (e: Exception) {
                _state.value = ChatListState(
                    isLoading = false,
                    chats = emptyList(),
                    error = e.message
                )
            }
        }
    }

    /**
     * Called by UI via ChatListScreenRoot.
     * Returns navigation route or null.
     */
    fun onAction(action: ChatListAction): String? {
        return when (action) {

            is ChatListAction.OpenChat -> {
                // navigate using router.push("chat/{chatId}")
                "chat/${action.chatId}"
            }

            ChatListAction.Refresh -> {
                loadChats()
                null
            }

            ChatListAction.Back -> {
                "back"
            }
        }
    }
}
