package com.example.designsystem.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.neosoft.designsystem.components.Avatar

data class Participant(val id:String, val name:String, val avatar:String?, val role:String="Listener")

@Composable
fun ParticipantGrid(items: List<Participant>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(columns = GridCells.Fixed(5), modifier = modifier.heightIn(min = 120.dp, max = 320.dp).fillMaxWidth(), contentPadding = PaddingValues(6.dp)) {
        items(items) { p ->
            Column(horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally, modifier = Modifier.padding(6.dp)) {
                Avatar(p.avatar, sizeDp = 36)
                Spacer(Modifier.height(6.dp))
                Text(p.name, fontSize = 11.sp, maxLines = 1)
                Text(p.role, fontSize = 10.sp, color = androidx.compose.ui.graphics.Color(0xFF7B7F85))
            }
        }
    }
}
