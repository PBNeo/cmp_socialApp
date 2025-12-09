package com.neosoft.chat.presentation.list


sealed interface ChatListAction {
    object Refresh : ChatListAction
    data class OpenChat(val chatId: String) : ChatListAction
    object Back : ChatListAction
}
