package com.neosoft.chat.presentation.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.neosoft.chat.domain.entity.Chat
import com.neosoft.designsystem.components.BaseScreen
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@Composable
fun ChatListScreen(chats: List<Chat>, onOpenChat: (String) -> Unit) {
    BaseScreen(title = "Chats"){
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Spacer(modifier = Modifier.height(8.dp))
            Column {
                chats.forEach { chat ->
                    Row(
                        modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onOpenChat(chat.id) }
                        .padding(vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (!chat.avatarUrl.isNullOrBlank()) {
                            AsyncImage(
                                model = chat.avatarUrl,
                                contentDescription = null,
                                modifier = Modifier.size(48.dp).clip(CircleShape)
                            )
                        } else {
                            Box(
                                modifier = Modifier.size(48.dp).clip(CircleShape)
                                    .background(Color.LightGray),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(chat.name.firstOrNull()?.toString() ?: "U")
                            }
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(chat.name)
                            Text(chat.lastMessage ?: "", fontSize = 12.sp, color = Color.Gray)
                        }
                        Column(horizontalAlignment = Alignment.End) {
                            Text(
                                formatShortDate(chat.lastTimestamp),
                                fontSize = 12.sp,
                                color = Color.Gray
                            )
                            if (chat.unreadCount > 0) {
                                Box(
                                    modifier = Modifier.background(Color(0xFF2D6A6F)).padding(6.dp)
                                ) {
                                    Text(
                                        chat.unreadCount.toString(),
                                        color = Color.White,
                                        fontSize = 12.sp
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalTime::class)
fun formatShortDate(ts: Long): String {
    val now = Clock.System.now().toEpochMilliseconds()
    return  ""
    }
