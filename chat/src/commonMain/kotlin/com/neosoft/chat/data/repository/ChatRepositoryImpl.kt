package neosoft.chat.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import com.neosoft.chat.data.remote.ChatWebSocketService
import com.neosoft.chat.data.remote.WsChatMessage
import com.neosoft.chat.data.remote.WsEnvelope
import com.neosoft.chat.domain.entity.Chat
import com.neosoft.chat.domain.entity.ChatMessage
import com.neosoft.chat.domain.repository.ChatRepository

class ChatRepositoryImpl(
    private val wsService: ChatWebSocketService
) : ChatRepository {

    override suspend fun connect(authToken: String?) {
        wsService.connect(authToken)
    }

    override suspend fun disconnect() {
        wsService.disconnect()
    }

    override suspend fun sendMessage(message: ChatMessage) {
        val ws = WsChatMessage(
            id = message.id,
            chatId = message.chatId,
            from = message.from,
            to = message.to,
            content = message.content,
            timestamp = message.timestamp,
            messageType = message.type
        )
        wsService.sendChatMessage(ws)
    }

    override fun incomingMessages(): Flow<ChatMessage> {
        return wsService.incomingFlow().map { raw ->
            val env = Json.decodeFromString<WsEnvelope>(raw)
            when (env.type) {
                "chat_message" -> {
                    val m = Json.decodeFromString<WsChatMessage>(env.payload)
                    ChatMessage(
                        id = m.id,
                        chatId = m.chatId,
                        from = m.from,
                        to = m.to,
                        content = m.content,
                        timestamp = m.timestamp,
                        type = m.messageType
                    )
                }
                else -> {
                    ChatMessage(id = "", chatId = "", from = "", to = "", content = env.payload, timestamp = System.currentTimeMillis(), type = "system")
                }
            }
        }
    }

    override suspend fun getChats(): List<Chat> = withContext(Dispatchers.IO) {
        listOf(
            Chat(id = "c1", name = "Abdul Quayyum", avatarUrl = null, lastMessage = "Hey, how are you?", unreadCount = 3, lastTimestamp = System.currentTimeMillis() - 3600_000),
            Chat(id = "c2", name = "Chris Uil", avatarUrl = null, lastMessage = "Check this", unreadCount = 0, lastTimestamp = System.currentTimeMillis() - 86_400_000)
        )
    }

    override suspend fun getMessages(chatId: String): List<ChatMessage> = withContext(Dispatchers.IO) {
        listOf(
            ChatMessage(id = "m1", chatId = chatId, from = "u2", to = "u1", content = "Hi, How are you today?", timestamp = System.currentTimeMillis() - 3600_000, type = "text"),
            ChatMessage(id = "m2", chatId = chatId, from = "u1", to = "u2", content = "I'm fine what about you?", timestamp = System.currentTimeMillis() - 3500_000, type = "text")
        )
    }
}
