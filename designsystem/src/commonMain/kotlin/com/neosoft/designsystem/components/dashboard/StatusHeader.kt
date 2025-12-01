package com.neosoft.designsystem.components.dashboard
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Badge
import androidx.compose.ui.unit.sp

/**
 * Top overlay header used on Status/Story screen
 *
 * @param avatarUrl small avatar image url
 * @param author display name
 * @param timeAgo "1h ago"
 * @param viewersCount number of viewers (optional)
 * @param isLive whether to show LIVE badge
 * @param onAvatarClick optional click
 * @param onClose close action (required)
 */
@Composable
fun StatusHeader(
    avatarUrl: String?,
    author: String,
    timeAgo: String? = null,
    viewersCount: Int? = null,
    isLive: Boolean = false,
    onAvatarClick: (() -> Unit)? = null,
    onClose: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            // Avatar
            AsyncImage(
                model = avatarUrl,
                contentDescription = "avatar",
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .clickable(enabled = onAvatarClick != null) {
                        onAvatarClick?.invoke()
                    }
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = author, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Black)
                    if (isLive) {
                        Spacer(modifier = Modifier.width(8.dp))
                        // Live badge
                        Badge(
                            containerColor = Color.Red,
                            modifier = Modifier.padding(start = 4.dp)
                        ) {
                            Text("LIVE", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }

                Row {
                    timeAgo?.let {
                        Text(text = it, fontSize = 12.sp, color = Color.White.copy(alpha = 0.85f))
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                    viewersCount?.let {
                        Text(text = "${it} watching", fontSize = 12.sp, color = Color.White.copy(alpha = 0.85f))
                    }
                }
            }
        }

        // Close button aligned right
        IconButton(
            onClick = onClose,
            modifier = Modifier
                .size(36.dp)
                .background(Color(0x33000000), shape = CircleShape)
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Close",
                tint = Color.White
            )
        }
    }
}