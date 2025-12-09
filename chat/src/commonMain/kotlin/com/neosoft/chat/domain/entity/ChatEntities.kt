package com.neosoft.chat.domain.entity

data class Chat(
    val id: String,
    val name: String,
    val avatarUrl: String?,
    val lastMessage: String?,
    val unreadCount: Int,
    val lastTimestamp: Long
)

data class ChatMessage(
    val id: String,
    val chatId: String,
    val from: String,
    val to: String,
    val content: String,
    val timestamp: Long,
    val type: String = "text"
)

data class ChatUser(
    val userId: String,
    val name: String,
    val avatarUrl: String?
)
