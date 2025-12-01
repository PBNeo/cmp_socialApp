package com.neosoft.dashboard.presentation.bottom

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BottomBar(onHome: () -> Unit, onStatus: () -> Unit, onProfile: () -> Unit) {
    Row(modifier = Modifier.padding(12.dp).fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        IconButton(onClick = onHome) { Icon(imageVector = Icons.Default.Home, contentDescription = "Home") }
        IconButton(onClick = onStatus) { Icon(imageVector = Icons.Default.Circle, contentDescription = "Status") }
        IconButton(onClick = onProfile) { Icon(imageVector = Icons.Default.Person, contentDescription = "Profile") }
    }
}