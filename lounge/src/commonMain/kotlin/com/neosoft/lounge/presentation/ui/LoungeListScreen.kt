package com.neosoft.lounge.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.neosoft.lounge.domain.entity.Lounge

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoungeListScreen(
    lounges: List<Lounge>,
    onOpenLounge: (String) -> Unit,
    onCreate: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        TopAppBar(title = { Text("Ofofo") })
        Spacer(modifier = Modifier.height(12.dp))
        // search placeholder
        Box(modifier = Modifier.fillMaxWidth().height(48.dp).clip(CircleShape).background(Color(0xFFF1F4F5)), contentAlignment = Alignment.CenterStart) {
            Text("Type something........", modifier = Modifier.padding(start = 16.dp), color = Color.Gray)
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text("Feeling bored?Join an Ofofo", fontSize = 16.sp)
        Spacer(modifier = Modifier.height(12.dp))

        Column(modifier = Modifier.fillMaxSize()) {
            lounges.forEach { l ->
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable { onOpenLounge(l.id) }
                    .background(Color(0xFFBBD8DB))
                    .padding(16.dp)
                ) {
                    Column {
                        Text("Live", color = Color.White, fontSize = 12.sp)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(l.title, fontSize = 16.sp, color = Color.White)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("${l.listenerCount} people listening", fontSize = 12.sp, color = Color.White)
                    }
                }
            }
        }

        FloatingActionButton(onClick = onCreate, modifier = Modifier.align(Alignment.End).padding(16.dp)) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Create")
        }
    }
}
