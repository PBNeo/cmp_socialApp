
package com.neosoft.designsystem.components.dashboard
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import androidx.compose.material3.Text
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Surface
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Close

/**
 * Full-screen media (image or video poster) used in Status/Story screen.
 *
 * @param mediaUrl image or video poster url
 * @param isVideo if true shows play button overlay
 * @param contentDescription image content description
 * @param modifier optional modifier
 * @param onPlay video play action (only used if isVideo)
 * @param onClose close action (top-right)
 */
@Composable
fun StatusMedia(
    mediaUrl: String?,
    isVideo: Boolean = false,
    contentDescription: String? = null,
    modifier: Modifier = Modifier,
    onPlay: (() -> Unit)? = null,
    onClose: (() -> Unit)? = null
) {
    Box(modifier = modifier.fillMaxSize()) {
        // Background media
        if (!mediaUrl.isNullOrBlank()) {
            AsyncImage(
                model = mediaUrl,
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        } else {
            // fallback surface
            Surface(modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray)) { }
        }

        // Top right close button (semi transparent)
        if (onClose != null) {
            IconButton(
                onClick = onClose,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(12.dp)
                    .size(36.dp)
                    .shadow(4.dp, CircleShape)
                    .clip(CircleShape)
                    .background(Color(0x66000000))
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close",
                    tint = Color.White
                )
            }
        }

        // center play button for video
        if (isVideo && onPlay != null) {
            IconButton(
                onClick = onPlay,
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(72.dp)
                    .shadow(8.dp, CircleShape)
                    .clip(CircleShape)
                    .background(Color(0x99000000))
            ) {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = "Play",
                    tint = Color.White,
                    modifier = Modifier.size(44.dp)
                )
            }
        }

        // bottom gradient (for readable overlay text)
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(220.dp)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color(0x66000000), Color(0xCC000000))
                    )
                )
        )
    }
}