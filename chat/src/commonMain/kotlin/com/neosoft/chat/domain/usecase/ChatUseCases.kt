package com.neosoft.chat.domain.usecase

import kotlinx.coroutines.flow.Flow
import com.neosoft.chat.domain.entity.Chat
import com.neosoft.chat.domain.entity.ChatMessage
import com.neosoft.chat.domain.repository.ChatRepository

class ConnectWebSocketUseCase(private val repository: ChatRepository) {
    suspend operator fun invoke(authToken: String?) = repository.connect(authToken)
}

class DisconnectWebSocketUseCase(private val repository: ChatRepository) {
    suspend operator fun invoke() = repository.disconnect()
}

class SendMessageUseCase(private val repository: ChatRepository) {
    suspend operator fun invoke(message: ChatMessage) = repository.sendMessage(message)
}

class ObserveMessagesUseCase(private val repository: ChatRepository) {
    operator fun invoke(): Flow<ChatMessage> = repository.incomingMessages()
}

class GetChatsUseCase(private val repository: ChatRepository) {
    suspend operator fun invoke(): List<Chat> = repository.getChats()
}

class GetMessagesUseCase(private val repository: ChatRepository) {
    suspend operator fun invoke(chatId: String): List<ChatMessage> = repository.getMessages(chatId)
}
