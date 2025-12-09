package com.neosoft.profile.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest


import com.neosoft.profile.domain.entity.FullProfile
import com.neosoft.profile.domain.entity.FollowerPreview
import com.neosoft.profile.domain.entity.UserPost

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    profile: FullProfile,
    onBack: () -> Unit = {},
    onFollowToggle: (Boolean) -> Unit = {},
    onMessage: () -> Unit = {},
    onMenu: () -> Unit = {}
) {
    LazyColumn(modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp)) {
        item {
            Spacer(modifier = Modifier.height(12.dp))
            TopAppBar(title = { Text(text = "Profile") }, navigationIcon = {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back", modifier = Modifier.size(24.dp).clickable { onBack() })
            })

            Spacer(modifier = Modifier.height(8.dp))

            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Top) {
                AvatarImage(url = profile.avatarUrl, size = 72.dp)

                Spacer(modifier = Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(text = profile.name, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    profile.location?.let { Text(text = it, fontSize = 12.sp, color = Color.Gray) }

                    Spacer(modifier = Modifier.height(8.dp))

                    profile.bio?.let {
                        Text(text = it, maxLines = 3, overflow = TextOverflow.Ellipsis, fontSize = 13.sp)
                    }
                }

                Column(horizontalAlignment = Alignment.End) {
                    TextButton(onClick = onMenu) { Text("...") }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
                StatItem(count = profile.postsCount, label = "Posts")
                StatItem(count = profile.followingCount, label = "Following")
                StatItem(count = profile.followersCount, label = "Followers")
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                OutlinedButton(onClick = { onMessage() }, modifier = Modifier.weight(1f)) {
                    Text("Message")
                }

                Spacer(modifier = Modifier.width(8.dp))

                val followText = if (profile.isFollowing) "Following" else "Follow"
                Button(onClick = { onFollowToggle(!profile.isFollowing) }, modifier = Modifier.weight(1f), colors = ButtonDefaults.buttonColors()) {
                    Text(followText)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Followers", fontWeight = FontWeight.Medium)

            Spacer(modifier = Modifier.height(8.dp))

            FollowersRow(profile.followersPreview)

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Posts", fontWeight = FontWeight.Medium)
            Spacer(modifier = Modifier.height(8.dp))
        }

        item {
            // Posts grid
            LazyVerticalGrid(columns = GridCells.Fixed(3), modifier = Modifier.heightIn(min = 200.dp).fillMaxWidth()) {
                items(profile.posts) { post ->
                    Box(modifier = Modifier.padding(4.dp).aspectRatio(1f)) {
                        AsyncImage(
                            model = post.imageUrl,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(8.dp))
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(80.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyProfileScreen(profile: FullProfile, onEditProfile: () -> Unit = {}, onBack: () -> Unit = {}) {
    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        item {
            TopAppBar(title = { Text("My Profile") }, navigationIcon = {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back", modifier = Modifier.size(24.dp).clickable { onBack() })
            })

            Spacer(modifier = Modifier.height(12.dp))

            Row(verticalAlignment = Alignment.Top) {
                AvatarImage(url = profile.avatarUrl, size = 72.dp)
                Spacer(modifier = Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = profile.name, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    profile.location?.let { Text(text = it, fontSize = 12.sp, color = Color.Gray) }
                    Spacer(modifier = Modifier.height(8.dp))
                    profile.bio?.let { Text(text = it, maxLines = 3, overflow = TextOverflow.Ellipsis, fontSize = 13.sp) }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(onClick = onEditProfile, modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors()) {
                Text("Edit Profile")
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
                StatItem(count = profile.postsCount, label = "Posts")
                StatItem(count = profile.followingCount, label = "Following")
                StatItem(count = profile.followersCount, label = "Followers")
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(text = "Posts", fontWeight = FontWeight.Medium)
            Spacer(modifier = Modifier.height(8.dp))
        }

        item {
            LazyVerticalGrid(columns = GridCells.Fixed(3), modifier = Modifier.heightIn(min = 200.dp)) {
                items(profile.posts) { post ->
                    Box(modifier = Modifier.padding(4.dp).aspectRatio(1f)) {
                        AsyncImage(model = post.imageUrl, contentDescription = null, contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(8.dp)))
                    }
                }
            }
            Spacer(modifier = Modifier.height(80.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(initial: FullProfile, onBack: () -> Unit = {}, onSave: (FullProfile) -> Unit = {}) {
    val nameState = remember { mutableStateOf(initial.name) }
    val regionState = remember { mutableStateOf(initial.location ?: "") }
    val phoneState = remember { mutableStateOf("") }
    val genderState = remember { mutableStateOf("") }
    val aboutState = remember { mutableStateOf(initial.bio ?: "") }

    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        item {
            TopAppBar(title = { Text("My Profile") }, navigationIcon = {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back", modifier = Modifier.size(24.dp).clickable { onBack() })
            })

            Spacer(modifier = Modifier.height(12.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                AvatarImage(url = initial.avatarUrl, size = 72.dp)
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(text = initial.name, fontWeight = FontWeight.Bold)
                    Text(text = initial.location ?: "", color = Color.Gray, fontSize = 12.sp)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextField(value = nameState.value, onValueChange = { nameState.value = it }, label = { Text("Username") }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))
            TextField(value = regionState.value, onValueChange = { regionState.value = it }, label = { Text("Region") }, modifier = Modifier.fillMaxWidth())

            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                TextField(value = phoneState.value, onValueChange = { phoneState.value = it }, label = { Text("Phone Number") }, modifier = Modifier.weight(1f))
                Spacer(modifier = Modifier.width(8.dp))
                TextField(value = genderState.value, onValueChange = { genderState.value = it }, label = { Text("Gender") }, modifier = Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(8.dp))
            TextField(value = aboutState.value, onValueChange = { aboutState.value = it }, label = { Text("About") }, modifier = Modifier.fillMaxWidth().height(120.dp))

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                val updated = initial.copy(name = nameState.value, location = regionState.value, bio = aboutState.value)
                onSave(updated)
            }, modifier = Modifier.fillMaxWidth()) {
                Text("Update")
            }

            Spacer(modifier = Modifier.height(80.dp))
        }
    }
}

@Composable
fun AvatarImage(url: String?, size: androidx.compose.ui.unit.Dp = 56.dp) {
    if (!url.isNullOrBlank()) {
        AsyncImage(model = url, contentDescription = null, contentScale = ContentScale.Crop, modifier = androidx.compose.ui.Modifier.size(size).clip(CircleShape).shadow(4.dp, CircleShape))
    } else {
        androidx.compose.material3.Surface(shape = CircleShape, color = Color.LightGray, modifier = androidx.compose.ui.Modifier.size(size)) {
            Box(contentAlignment = Alignment.Center) { Text(text = "U", fontWeight = FontWeight.Bold) }
        }
    }
}

@Composable
fun StatItem(count: Int, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "$count", fontWeight = FontWeight.Bold)
        Text(text = label, fontSize = 12.sp, color = Color.Gray)
    }
}

@Composable
fun FollowersRow(items: List<FollowerPreview>) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        items(items.size) { idx ->
            val f = items[idx]
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.width(64.dp)) {
                AvatarImage(url = f.avatarUrl, size = 48.dp)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = f.name, fontSize = 12.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
            }
        }
    }
}
