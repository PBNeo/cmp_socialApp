package com.neosoft.chat.domain.repository

import kotlinx.coroutines.flow.Flow
import com.neosoft.chat.domain.entity.Chat
import com.neosoft.chat.domain.entity.ChatMessage

interface ChatRepository {
    suspend fun connect(authToken: String? = null)
    suspend fun disconnect()
    suspend fun sendMessage(message: ChatMessage)
    fun incomingMessages(): Flow<ChatMessage>
    suspend fun getChats(): List<Chat>
    suspend fun getMessages(chatId: String): List<ChatMessage>
}
