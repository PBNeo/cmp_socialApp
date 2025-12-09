package com.example.designsystem.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.neosoft.designsystem.components.Avatar

data class Lounge(val id:String, val title:String, val host:String, val hostAvatar:String?, val listeners:Int, val isLive:Boolean=true)

@Composable
fun LoungeCard(lounge: Lounge, onClick:(Lounge)->Unit={}) {
    Card(shape = RoundedCornerShape(14.dp), modifier = Modifier.fillMaxWidth().clickable { onClick(lounge) }) {
        Column(modifier = Modifier.padding(14.dp)) {
            if (lounge.isLive) Text("Live", color = androidx.compose.ui.graphics.Color(0xFF2F6D6F))
            Spacer(Modifier.height(8.dp))
            Text(lounge.title)
            Spacer(Modifier.height(8.dp))
            Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                Avatar(lounge.hostAvatar, sizeDp = 36)
                Spacer(Modifier.width(8.dp))
                Column { Text(lounge.host); Text("${lounge.listeners} people listening", color = androidx.compose.ui.graphics.Color(0xFF7B7F85)) }
            }
        }
    }
}
