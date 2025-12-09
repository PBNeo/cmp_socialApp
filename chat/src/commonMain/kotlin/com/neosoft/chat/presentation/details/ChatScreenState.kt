package com.neosoft.chat.presentation.details

import com.neosoft.chat.domain.entity.ChatMessage

data class ChatState(
    val isConnected: Boolean = false,
    val isLoading: Boolean = false,
    val messages: List<ChatMessage> = emptyList(),
    val typingUsers: List<String> = emptyList(), // userIds typing
    val error: String? = null
)
