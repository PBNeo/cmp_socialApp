package com.neosoft.designsystem.components.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CommentItem(avatarUrl: String?, name: String, text: String, time: String, likes: Int = 0, modifier: Modifier = Modifier) {
    Row(modifier = modifier.fillMaxWidth().padding(12.dp), verticalAlignment = Alignment.Top) {
        CircleAvatar(avatarUrl, size = 40)
        Spacer(Modifier.width(10.dp))
        Column(modifier = Modifier.weight(1f)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(name, fontWeight = FontWeight.SemiBold)
                Spacer(Modifier.weight(1f))
                Text(time, color = Color(0xFF7B7F85), fontSize = 12.sp)
            }
            Spacer(Modifier.height(6.dp))
            Text(text)
            Spacer(Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.FavoriteBorder, contentDescription = "Like", tint = Color(0xFF7B7F85), modifier = Modifier.size(18.dp))
                Spacer(Modifier.width(6.dp))
                Text("$likes", color = Color(0xFF7B7F85), fontSize = 12.sp)
            }
        }
    }
}

@Composable
fun CircleAvatar(x0: String?, size: Int) {
    TODO("Not yet implemented")
}
