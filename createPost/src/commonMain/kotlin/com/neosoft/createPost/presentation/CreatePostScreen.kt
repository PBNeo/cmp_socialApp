package com.neosoft.createPost.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.neosoft.designsystem.components.BaseScreen

data class CreatePostScreenState(
    val isLoading: Boolean = false,
    val images: List<String> = emptyList(), // local URIs or urls
    val caption: String = "",
    val hashtags: String = "",
    val success: Boolean = false,
    val error: String? = null
)

@Composable
fun CreatePostScreen(
    state: CreatePostScreenState,
    onPickImages: () -> Unit,
    onCaptionChange: (String) -> Unit,
    onHashtagsChange: (String) -> Unit,
    onUpload: () -> Unit
) {
    BaseScreen{
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            Text(text = "Select Image(s)", fontSize = 14.sp)
            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .border(1.dp, Color(0xFFCED7DB), RoundedCornerShape(8.dp))
                    .clickable { onPickImages() },
                contentAlignment = Alignment.Center
            ) {
                if (state.images.isEmpty()) {
                    Text("Tap to add", color = Color.Gray)
                } else {
                    Text("${state.images.size} image(s)")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Add caption", fontSize = 14.sp)
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = state.caption,
                onValueChange = onCaptionChange,
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Write a caption...") })

            Spacer(modifier = Modifier.height(12.dp))

            Text(text = "Add hashtags", fontSize = 14.sp)
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = state.hashtags,
                onValueChange = onHashtagsChange,
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("#food #travel") })

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = onUpload, modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text("Upload")
            }

            if (state.isLoading) {
                Spacer(modifier = Modifier.height(12.dp))
                Text("Uploading...", color = Color.Gray)
            }

            state.error?.let {
                Spacer(modifier = Modifier.height(8.dp))
                Text(it, color = Color.Red)
            }

            if (state.success) {
                Spacer(modifier = Modifier.height(8.dp))
                Text("Uploaded successfully", color = Color.Green)
            }
        }
    }
}
