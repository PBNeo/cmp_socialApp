package com.neosoft.lounge.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.neosoft.lounge.domain.entity.LoungeDetails

@Composable
fun LoungeRoomSheet(details: LoungeDetails?, onClose: () -> Unit) {
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        details?.let { d ->
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(model = null, contentDescription = null, modifier = Modifier.size(64.dp).clip(CircleShape))
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(d.hostName, fontSize = 16.sp)
                    Text("Host", fontSize = 12.sp, color = Color.Gray)
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text("Listeners", modifier = Modifier.padding(vertical = 8.dp))
            LazyVerticalGrid(columns = GridCells.Fixed(4), modifier = Modifier.height(240.dp)) {
                items(d.participants.size) { idx ->
                    val p = d.participants[idx]
                    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(8.dp)) {
                        AsyncImage(model = p.avatarUrl, contentDescription = null, modifier = Modifier.size(48.dp).clip(CircleShape))
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(p.name, fontSize = 12.sp)
                        Text(p.role, fontSize = 10.sp, color = Color.Gray)
                    }
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = { /* stop */ }, modifier = Modifier.fillMaxWidth()) {
                Text("Stop")
            }
        }
    }
}
