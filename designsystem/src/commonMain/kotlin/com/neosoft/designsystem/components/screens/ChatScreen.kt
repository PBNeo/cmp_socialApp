//package com.neosoft.designsystem.components.screens
//
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.material3.SmallTopAppBar
//import androidx.compose.runtime.Composable
//import com.example.designsystem.base.BaseState
//import com.example.designsystem.components.ChatBubble
//import com.example.designsystem.models.ChatMessage
//
//data class ChatState(val messages: List<ChatMessage> = emptyList())
//sealed interface ChatAction { object OnBack: ChatAction; object OnSend: ChatAction }
//
//@Composable
//fun ChatScreen(state: BaseState<ChatState>, onAction:(ChatAction)->Unit) {
//    Scaffold(topBar = { SmallTopAppBar(title = { Text("Chat") }, navigationIcon = { IconButton(onClick = { onAction(ChatAction.OnBack) }) { Icon(Icons.Default.ArrowBack, contentDescription = "Back") } }) }) {
//        val messages = state.data?.messages ?: emptyList()
//        LazyColumn { items(messages) { m -> ChatBubble(message = com.example.designsystem.components.ChatMessage(id = m.id, fromId = m.fromId, text = m.text, imageUrl = m.imageUrl, time = m.time, outgoing = m.outgoing)) } }
//    }
//}
