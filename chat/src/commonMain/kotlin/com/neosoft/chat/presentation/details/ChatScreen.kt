package com.neosoft.chat.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
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
import com.neosoft.chat.domain.entity.ChatMessage
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

@Composable
fun ChatScreen(
    messages: List<ChatMessage>,
    meId: String,
    onSend: (String) -> Unit,
    onBack: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier.fillMaxWidth().padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onBack) { /* Back */ }
            Text("Abdul Quayyum", fontSize = 18.sp)
        }

        LazyColumn(modifier = Modifier.weight(1f).padding(horizontal = 12.dp)) {
            items(messages) { msg ->
                val isMe = msg.from == meId
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = if (isMe) Arrangement.End else Arrangement.Start) {
                    Box(
                        modifier = Modifier
                            .padding(vertical = 6.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(if (isMe) Color(0xFF2D6A6F) else Color(0xFFF0F0F0))
                            .padding(12.dp)
                            .widthIn(max = 280.dp)
                    ) {
                        Column {
                            Text(msg.content, color = if (isMe) Color.White else Color.Black)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(formatTime(msg.timestamp), fontSize = 10.sp, color = if (isMe) Color(0xFFBEE3E0) else Color.Gray)
                        }
                    }
                }
            }
        }

        val textState = remember { mutableStateOf("") }
        Row(modifier = Modifier.fillMaxWidth().padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { /* pick media */ }) {
                Text("+")
            }
            TextField(value = textState.value, onValueChange = { textState.value = it }, modifier = Modifier.weight(1f), placeholder = { Text("Type a comment") })
            IconButton(onClick = {
                if (textState.value.isNotBlank()) {
                    onSend(textState.value)
                    textState.value = ""
                }
            }) {
                Text("Send")
            }
        }
    }
}

fun formatTime(ts: Long): String {
    return ""
}
