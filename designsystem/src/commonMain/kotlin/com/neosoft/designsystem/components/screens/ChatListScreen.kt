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
//import com.example.designsystem.components.ChatListItem
//import com.example.designsystem.models.UserModel
//
//data class ChatListState(val users: List<UserModel> = emptyList())
//sealed interface ChatListAction { object OnBack: ChatListAction; data class OnOpen(val id:String):ChatListAction }
//
//@Composable
//fun ChatListScreen(state: BaseState<ChatListState>, onAction:(ChatListAction)->Unit) {
//    Scaffold(topBar = { SmallTopAppBar(title = { Text("Chats") }, navigationIcon = { IconButton(onClick = { onAction(ChatListAction.OnBack) }) { Icon(Icons.Default.ArrowBack, contentDescription = "Back") } }) }) {
//        val users = state.data?.users ?: emptyList()
//        LazyColumn { items(users) { u -> ChatListItem(user = com.example.designsystem.models.UserModel(u.id, u.name, u.avatarUrl), lastMessage = "Hi", time = "08:43", unreadCount = 0, onClick = { onAction(ChatListAction.OnOpen(u.id)) }) } }
//    }
//}
