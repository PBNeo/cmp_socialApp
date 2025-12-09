package com.example.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.neosoft.designsystem.components.components.CircleAvatar

@Composable
fun StoryCarousel(items: List<Pair<String, String?>>, onCreate: () -> Unit = {}, onOpen: (index: Int) -> Unit = {}) {
    LazyRow(contentPadding = PaddingValues(horizontal = 12.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        item {
            Column(horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally) {
                Box(modifier = Modifier.size(64.dp).clip(androidx.compose.foundation.shape.CircleShape).background(androidx.compose.ui.graphics.Color(0xFFF6F8F8)).clickable(onClick = onCreate), contentAlignment = androidx.compose.ui.Alignment.Center) {
                    Icon(Icons.Default.Add, contentDescription = "Create")
                }
                Spacer(Modifier.height(6.dp)); Text("You", fontSize = 12.sp)
            }
        }
        items(items.size) { index ->
            val (name, url) = items[index]
            Column(horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally, modifier = Modifier.clickable { onOpen(index) }) {
                CircleAvatar(url, size = 64)
                Spacer(Modifier.height(6.dp)); Text(name, fontSize = 12.sp, maxLines = 1, overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis)
            }
        }
    }
}
