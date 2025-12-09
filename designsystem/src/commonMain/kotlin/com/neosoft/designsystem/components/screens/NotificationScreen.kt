//package com.neosoft.designsystem.components.screens
//
//import androidx.compose.foundation.layout.PaddingValues
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.material3.TopAppBarDefaults
//import androidx.compose.material3.SmallTopAppBar
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import com.example.designsystem.base.BaseState
//import com.example.designsystem.components.NotificationRow
//
//data class NotificationItem(val id:String, val avatar:String?, val title:String, val subtitle:String)
//
//data class NotificationScreenState(val items: List<NotificationItem> = emptyList(), val loading:Boolean = false, val error:String?=null)
//sealed interface NotificationScreenAction {
//    object OnBack: NotificationScreenAction
//    object OnNext: NotificationScreenAction
//    data class OnOpen(val id:String): NotificationScreenAction
//}
//
//@Composable
//fun NotificationScreen(state: BaseState<NotificationScreenState>, onAction:(NotificationScreenAction)->Unit) {
//    Scaffold(topBar = {
//        SmallTopAppBar(title = { Text("Notifications") }, navigationIcon = {
//            IconButton(onClick = { onAction(NotificationScreenAction.OnBack) }) { Icon(Icons.Default.ArrowBack, contentDescription = "Back") }
//        }, actions = {
//            IconButton(onClick = {}) { Icon(Icons.Default.Delete, contentDescription = "Delete") }
//        }, scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior())
//    }) { padding ->
//        LazyColumn(contentPadding = PaddingValues(12.dp), modifier = Modifier) {
//            val items = state.data?.items ?: emptyList()
//            items(items) { it ->
//                NotificationRow(avatarUrl = it.avatar, title = it.title, subtitle = it.subtitle)
//            }
//        }
//    }
//}
