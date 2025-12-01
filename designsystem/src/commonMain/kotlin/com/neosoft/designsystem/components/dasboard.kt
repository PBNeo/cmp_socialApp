package com.neosoft.designsystem.components
import AvatarRow
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.neosoft.designsystem.components.dashboard.CommentModel
import com.neosoft.designsystem.utils.AppColors.grey


@Composable
fun Avatar(
    url: String?,
    sizeDp: Int = 40,
    onClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier,        // <-- allow parent to position it
    hasBorder: Boolean = false,
    borderColor: Color = Color.White,
    borderWidth: Dp = 2.dp
) {
    val baseModifier = modifier
        .size(sizeDp.dp)
        .clip(CircleShape)

    val finalModifier = if (hasBorder) {
        baseModifier
            .border(borderWidth, borderColor, CircleShape)
    } else baseModifier

    if (onClick != null) {
        Box(modifier = finalModifier.clickable { onClick() }) {
            AsyncImage(
                model = url,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
    } else {
        AsyncImage(
            model = url,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = finalModifier
        )
    }
}


@Composable
fun StoryRow(items: List<PostModel>, onStoryClick: (PostModel) -> Unit) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        items(items) { story ->
            Column(
                modifier = Modifier.width(75.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier.size(height = 100.dp, width = 75.dp) // Parent box
                ) {
                    // Image with rounded corners
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(12.dp))
                    ) {
                        AsyncImage(
                            model = story.imageUrl,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    // Avatar overlapping at bottom-center
                    Avatar(
                        url = story.avatarUrl,
                        sizeDp = 24,
                        onClick = { onStoryClick(story) },
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .offset(y = 12.dp) // half outside the image
                    )
                }

                Spacer(Modifier.height(16.dp))
                Text(
                    text = story.author,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}


@Composable
fun PostCard(post:PostModel, onOpenStatus: () -> Unit,onViewMoreComments: (comments: List<CommentModel>) -> Unit) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = grey // your desired background color
        ),
        shape = RoundedCornerShape(12.dp), modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Avatar(url = post.avatarUrl, sizeDp = 48, onClick = onOpenStatus)
                Spacer(Modifier.width(8.dp))
                Column {
                    Text(text = post.author)
                    Text(text = post.timeAgo)
                }
            }
            Spacer(Modifier.height(8.dp))
            post.text?.let { Text(it) }
            post.imageUrl?.let {
                Spacer(Modifier.height(8.dp))
                AsyncImage(model = it, contentDescription = null, modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp), contentScale = ContentScale.Crop)
            }
            Spacer(Modifier.height(8.dp))

            Row (horizontalArrangement = Arrangement.SpaceBetween,){
                Column (
                    modifier = Modifier.weight(1f)
                ){
                    if (!post.commentsList.isNullOrEmpty()) {
                        AvatarRow(
                            items = post.commentsList!!,
                            onAvatarClick = {},
                            avatarSizeDp = 20,
                            maxVisible = 3,
                            modifier = Modifier.weight(1f) //
                        )
                        Spacer(Modifier.height(8.dp))
                        Text(
                            text = "View all comments",
                            color = Color.Gray,
                            modifier = Modifier.clickable { onViewMoreComments(post.commentsList!!)}

                        )
                    }
                }
                Row(horizontalArrangement = Arrangement.End) {
                    Text("${post.likes} ❤")
                    Spacer(Modifier.width(8.dp))
                    Text("${post.comments} 💬")
                }
            }

        }
    }
}

@Composable
fun CommentInput(onSend: (String) -> Unit) {
    var value by remember { mutableStateOf("") }
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(4.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AppTextField(value = value, onValueChange = { value = it }, label = "Type a comment")
        AppPrimaryButton(text = "Send", onClick = {
            if (value.isNotBlank()) {
                onSend(value)
                value = ""
            }
        })
    }
}