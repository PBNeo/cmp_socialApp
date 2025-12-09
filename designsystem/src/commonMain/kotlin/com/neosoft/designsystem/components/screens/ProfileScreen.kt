//package com.neosoft.designsystem.components.screens
//
//import androidx.compose.foundation.layout.Column
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.material3.TopAppBar
//import androidx.compose.runtime.Composable
//import com.example.designsystem.base.BaseState
//import com.example.designsystem.components.PostCardModel
//import com.example.designsystem.components.PostCard
//import com.example.designsystem.components.Avatar
//
//data class ProfileState(val userId:String = "", val name:String = "", val bio:String = "", val posts: List<PostCardModel> = emptyList())
//sealed interface ProfileAction { object OnBack: ProfileAction }
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun ProfileScreen(state: BaseState<ProfileState>, onAction:(ProfileAction)->Unit) {
//    Scaffold(topBar = { TopAppBar(title = { Text("Profile") }, navigationIcon = null) }) {
//        val d = state.data
//        Column { if (d!=null) { Avatar(null); Text(d.name); Text(d.bio); d.posts.forEach { PostCard(it) } } }
//    }
//}
