//package com.example.designsystem.screens
//
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.material3.SmallTopAppBar
//import androidx.compose.runtime.Composable
//import com.example.designsystem.base.BaseState
//import com.example.designsystem.components.PostCardModel
//import com.example.designsystem.components.PostCard
//import com.example.designsystem.components.StoryCarousel
//
//data class FeedState(val stories: List<Pair<String,String?>> = emptyList(), val posts: List<PostCardModel> = emptyList())
//sealed interface FeedAction { object OnBack: FeedAction }
//
//@Composable
//fun FeedScreen(state: BaseState<FeedState>, onAction:(FeedAction)->Unit) {
//    Scaffold(topBar = { SmallTopAppBar(title = { Text("Home") }, navigationIcon = null) }) {
//        val st = state.data
//        LazyColumn { item { StoryCarousel(st?.stories ?: emptyList()) }; val posts = st?.posts ?: emptyList(); items(posts) { PostCard(it) } }
//    }
//}
