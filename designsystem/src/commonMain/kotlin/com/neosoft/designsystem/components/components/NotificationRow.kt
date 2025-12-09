package com.example.designsystem.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.neosoft.designsystem.components.Avatar

@Composable
fun NotificationRow(avatarUrl: String?, title: String, subtitle: String, modifier: Modifier = Modifier) {
    Row(modifier = modifier.fillMaxWidth().padding(vertical = 10.dp, horizontal = 12.dp), verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
        Avatar(url = avatarUrl, sizeDp = 44)
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(title, fontWeight = androidx.compose.ui.text.font.FontWeight.SemiBold)
            Text(subtitle, color = androidx.compose.ui.graphics.Color(0xFF7B7F85), fontSize = 12.sp)
        }
        IconButton(onClick = {}) { Icon(Icons.Default.MoreVert, contentDescription = "More") }
    }
}
