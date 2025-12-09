package com.example.designsystem.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.neosoft.designsystem.components.Avatar

data class PostCardModel(val id: String, val userName: String, val userAvatar: String?, val time: String, val text: String?, val imageUrl: String?, val likes: Int = 0, val comments: Int = 0)

@Composable
fun PostCard(post: PostCardModel, onLike: (String) -> Unit = {}, onComment: (String) -> Unit = {}) {
    Card(shape = RoundedCornerShape(12.dp), modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                Avatar(post.userAvatar)
                Spacer(modifier = Modifier.width(10.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(post.userName)
                    Text(post.time, color = androidx.compose.ui.graphics.Color(0xFF7B7F85), fontSize = 12.sp)
                }
                IconButton(onClick = {}) { Icon(Icons.Default.MoreVert, contentDescription = "More") }
            }
            Spacer(modifier = Modifier.height(8.dp))
            post.text?.let { Text(it, maxLines = 3, overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis) }
            post.imageUrl?.let {
                Spacer(modifier = Modifier.height(8.dp))
                Card(shape = RoundedCornerShape(10.dp), modifier = Modifier.fillMaxWidth().height(180.dp)) {
                    AsyncImage(model = it, contentDescription = null, modifier = Modifier.fillMaxSize(), contentScale = androidx.compose.ui.layout.ContentScale.Crop)
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.Start, verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                IconButton(onClick = { onLike(post.id) }) { Icon(Icons.Default.FavoriteBorder, contentDescription = "Like", tint = androidx.compose.ui.graphics.Color(0xFF2F6D6F)) }
                Text("${post.likes}")
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(onClick = { onComment(post.id) }) { Icon(Icons.Default.ChatBubbleOutline, contentDescription = "Comment") }
                Text("${post.comments}")
            }
        }
    }
}
