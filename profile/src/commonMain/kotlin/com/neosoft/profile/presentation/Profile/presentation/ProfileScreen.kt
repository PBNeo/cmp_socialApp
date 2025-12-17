package com.neosoft.profile.presentation.Profile.presentation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.neosoft.designsystem.components.BaseScreen
import coil3.compose.AsyncImage
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material3.TextButton
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.foundation.background
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import neosoft.profile.ProfileScreenAction
import neosoft.profile.ProfileScreenState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(state: ProfileScreenState, onAction: (ProfileScreenAction) -> Unit) {
    BaseScreen {
        if (state.isLoading) {
            // you can show a loading composable from your design system
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Loading...")
            }
            return@BaseScreen
        }

        Column(modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp)) {
            Spacer(modifier = Modifier.height(8.dp))

            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Top) {
                if (!state.avatarUrl.isNullOrBlank()) {
                    AsyncImage(
                        model = state.avatarUrl,
                        contentDescription = null,
                        modifier = Modifier.size(72.dp).clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Box(
                        modifier = Modifier.size(72.dp).clip(CircleShape).background(Color.LightGray),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = state.name?.firstOrNull()?.toString() ?: "U", fontWeight = FontWeight.Bold)
                    }
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(text = state.name ?: "", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    state.location?.let { Text(text = it, fontSize = 12.sp, color = Color.Gray) }

                    Spacer(modifier = Modifier.height(8.dp))

                    state.bio?.let {
                        Text(text = it, maxLines = 3, overflow = TextOverflow.Ellipsis, fontSize = 13.sp)
                    }
                }

                TextButton(onClick = { onAction(ProfileScreenAction.OnNext) }) { Text("...") }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
                StatItem(count = state.postsCount, label = "Posts")
                StatItem(count = state.followingCount, label = "Following")
                StatItem(count = state.followersCount, label = "Followers")
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                OutlinedButton(onClick = { state.userId?.let { onAction(ProfileScreenAction.OpenMessage(it)) } }) {
                    Text("Message")
                }

                val followText = if (state.isFollowing) "Following" else "Follow"
                Button(onClick = { state.userId?.let { onAction(ProfileScreenAction.ToggleFollow(it)) } }) {
                    Text(followText)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text("Followers", fontWeight = FontWeight.Medium)
            Spacer(modifier = Modifier.height(8.dp))

            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(state.followersPreview) { f ->
                    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.width(64.dp)) {
                        if (!f.avatarUrl.isNullOrBlank()) {
                            AsyncImage(
                                model = f.avatarUrl,
                                contentDescription = null,
                                modifier = Modifier.size(48.dp).clip(CircleShape),
                                contentScale = ContentScale.Crop
                            )
                        } else {
                            Box(modifier = Modifier.size(48.dp).clip(CircleShape).background(Color.LightGray), contentAlignment = Alignment.Center) {
                                Text(f.name.firstOrNull()?.toString() ?: "U")
                            }
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(f.name, fontSize = 12.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text("Posts", fontWeight = FontWeight.Medium)
            Spacer(modifier = Modifier.height(8.dp))

            // simple grid
            LazyVerticalGrid(columns = GridCells.Fixed(3), modifier = Modifier.weight(1f)) {
                items(state.posts) { post ->
                    Box(modifier = Modifier.padding(4.dp).aspectRatio(1f)) {
                        AsyncImage(
                            model = post.imageUrl,
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun StatItem(count: Int, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "$count", fontWeight = FontWeight.Bold)
        Text(text = label, fontSize = 12.sp, color = Color.Gray)
    }
}
