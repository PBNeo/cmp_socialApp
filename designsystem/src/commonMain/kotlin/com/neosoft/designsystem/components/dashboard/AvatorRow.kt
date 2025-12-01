import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.neosoft.designsystem.components.Avatar
import com.neosoft.designsystem.components.PostModel
import com.neosoft.designsystem.components.dashboard.CommentModel
import com.neosoft.designsystem.utils.AppColors.primary


@Composable
fun AvatarRow(
    items: List<CommentModel>,
    onAvatarClick: (CommentModel) -> Unit,
    avatarSizeDp: Int = 40,
    maxVisible: Int = 3,
    modifier: Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier =Modifier.fillMaxWidth()
    ) {
        val displayItems = items.take(maxVisible)

        displayItems.forEach { item ->
            Avatar(
                url = item.avatarUrl,
                sizeDp = avatarSizeDp,
                hasBorder = true,
                borderColor = Color.White,
                onClick = { onAvatarClick(item) }
            )
        }

        if (items.size > maxVisible) {
            val remaining = items.size - maxVisible
            Box(
                modifier = Modifier
                    .size(avatarSizeDp.dp)
                    .clip(CircleShape)
                    .background(Color.Gray),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "+$remaining",
                    color = primary,
                    fontSize = (avatarSizeDp / 2.5).sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}


