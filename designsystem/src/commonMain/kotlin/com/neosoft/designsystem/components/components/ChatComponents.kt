package com.neosoft.designsystem.components.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.neosoft.designsystem.components.Avatar

data class ChatMessage(val id:String, val fromId:String, val text:String?, val imageUrl:String? = null, val time:String, val outgoing:Boolean = false)

@Composable
fun ChatListItem(user: com.example.designsystem.models.UserModel, lastMessage: String, time: String, unreadCount: Int = 0, onClick: () -> Unit = {}) {
    Row(modifier = Modifier.fillMaxWidth().clickable(onClick = onClick).padding(vertical = 10.dp, horizontal = 16.dp), verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
        Avatar(url = user.avatarUrl, sizeDp = 52)
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                Text(user.name, fontWeight = androidx.compose.ui.text.font.FontWeight.SemiBold)
                Spacer(modifier = Modifier.weight(1f))
                Text(time, color = androidx.compose.ui.graphics.Color(0xFF7B7F85), fontSize = 12.sp)
            }
            Spacer(Modifier.height(6.dp))
            Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                Text(text = lastMessage, maxLines = 1, overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis, color = androidx.compose.ui.graphics.Color(0xFF7B7F85))
                Spacer(modifier = Modifier.width(8.dp))
                if (unreadCount > 0) {
                    Box(modifier = Modifier.background(androidx.compose.ui.graphics.Color(0xFF2F6D6F), androidx.compose.foundation.shape.RoundedCornerShape(12.dp)).padding(horizontal = 8.dp, vertical = 4.dp)) {
                        Text("$unreadCount", color = androidx.compose.ui.graphics.Color.White, fontSize = 12.sp)
                    }
                }
            }
        }
    }
}

@Composable
fun ChatBubble(message: ChatMessage) {
    val bubbleColor = if (message.outgoing) androidx.compose.ui.graphics.Color(0xFF2F6D6F) else androidx.compose.ui.graphics.Color(0xFFF2F4F5)
    val textColor = if (message.outgoing) androidx.compose.ui.graphics.Color.White else androidx.compose.ui.graphics.Color.Black
    Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp, vertical = 6.dp), horizontalArrangement = if (message.outgoing) Arrangement.End else Arrangement.Start) {
        Column(horizontalAlignment = if (message.outgoing) Alignment.End else Alignment.Start) {
            message.text?.let {
                Box(modifier = Modifier.background(bubbleColor, androidx.compose.foundation.shape.RoundedCornerShape(16.dp)).padding(horizontal = 14.dp, vertical = 10.dp).widthIn(max = 260.dp)) {
                    Text(it, color = textColor)
                }
            }
            message.imageUrl?.let { img ->
                Spacer(modifier = Modifier.height(6.dp))
                androidx.compose.material3.Card(shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp), modifier = Modifier.size(width = 200.dp, height = 120.dp)) {
                    AsyncImage(model = img, contentDescription = null, modifier = Modifier.fillMaxSize(), contentScale = androidx.compose.ui.layout.ContentScale.Crop)
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(message.time, fontSize = 11.sp, color = androidx.compose.ui.graphics.Color(0xFF7B7F85))
        }
    }
}

@Composable
fun ChatInputBar(value: String, onValueChange: (String) -> Unit, onSend: (String) -> Unit, modifier: Modifier = Modifier) {
    Row(modifier = modifier.fillMaxWidth().padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
        IconButton(onClick = { }) { Icon(Icons.Default.Add, contentDescription = "Attach") }
        OutlinedTextField(value = value, onValueChange = onValueChange, placeholder = { Text("Type a comment") }, modifier = Modifier.weight(1f).height(52.dp), colors = TextFieldDefaults.colors( ))
        Spacer(modifier = Modifier.width(8.dp))
        IconButton(onClick = { if (value.isNotBlank()) { onSend(value); onValueChange("") } }) { Icon(Icons.Default.Send, contentDescription = "Send", tint = androidx.compose.ui.graphics.Color(0xFF2F6D6F)) }
    }
}
