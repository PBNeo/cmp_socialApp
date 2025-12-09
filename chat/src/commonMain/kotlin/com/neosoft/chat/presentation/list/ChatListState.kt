package com.neosoft.chat.presentation.list

import com.neosoft.chat.domain.entity.Chat

data class ChatListState(
    val isLoading: Boolean = false,
    val chats: List<Chat> = emptyList(),
    val error: String? = null
)
