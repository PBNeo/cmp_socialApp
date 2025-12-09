package com.neosoft.notification.presentation.Notification

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.neosoft.notification.domain.entity.NotificationItem


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationScreen(
    notifications: List<NotificationItem>,
    onBack: () -> Unit = {},
    onClearAll: () -> Unit = {}
) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        TopAppBar(
            title = { Text("Notifications") },
            navigationIcon = {
                Text("<", modifier = Modifier.clickable { onBack() })
            },
            actions = {
                Text("Clear", modifier = Modifier.clickable { onClearAll() })
            }
        )

        Spacer(modifier = Modifier.height(12.dp))

        // group before entering LazyColumn to keep lambda simple
        val grouped = notifications.groupBy { dateLabel(it.timestamp) }

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            grouped.forEach { (day, listForDay) ->
                // Day header
                item {
                    Text(day, modifier = Modifier.padding(vertical = 8.dp), fontSize = 14.sp)
                }

                // Emit all notifications for this day
                items(listForDay) { n ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (!n.avatarUrl.isNullOrBlank()) {
                            AsyncImage(
                                model = n.avatarUrl,
                                contentDescription = null,
                                modifier = Modifier.size(48.dp).clip(CircleShape),
                                contentScale = ContentScale.Crop
                            )
                        } else {
                            Box(
                                modifier = Modifier
                                    .size(48.dp)
                                    .clip(CircleShape)
                                    .background(Color.LightGray),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(n.title.firstOrNull()?.toString() ?: "U")
                            }
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(n.title, fontWeight = FontWeight.Bold)
                            Text(n.message, fontSize = 12.sp, color = Color.Gray)
                        }

                        Text(timeLabel(n.timestamp), fontSize = 12.sp, color = Color.Gray)
                    }
                }
            }
        }
    }
}


fun dateLabel(ts: Long): String {
    return "date"
}

fun timeLabel(ts: Long): String {
    return "time"
}
