package com.neosoft.chat.presentation.details
sealed interface ChatAction {
    data class SendText(val text: String) : ChatAction
    data class SendImage(val localUri: String) : ChatAction
    object Connect : ChatAction
    object Disconnect : ChatAction
    data class Typing(val isTyping: Boolean) : ChatAction
}
