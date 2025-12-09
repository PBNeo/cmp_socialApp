package com.neosoft.designsystem.components.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddBox
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun BottomNavBar(selectedIndex: Int = 0, onItemSelected: (Int) -> Unit = {}) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround, verticalAlignment = Alignment.CenterVertically) {
        NavIcon(icon = Icons.Default.Home, label = "Home", index = 0, selectedIndex = selectedIndex, onClick = onItemSelected)
        NavIcon(icon = Icons.Default.BarChart, label = "Stats", index = 1, selectedIndex = selectedIndex, onClick = onItemSelected)
        NavIcon(icon = Icons.Default.AddBox, label = "Create", index = 2, selectedIndex = selectedIndex, onClick = onItemSelected)
        NavIcon(icon = Icons.Default.Message, label = "Messages", index = 3, selectedIndex = selectedIndex, onClick = onItemSelected)
        NavIcon(icon = Icons.Default.Person, label = "Profile", index = 4, selectedIndex = selectedIndex, onClick = onItemSelected)
    }
}

@Composable
private fun NavIcon(icon: ImageVector, label: String, index: Int, selectedIndex: Int, onClick: (Int) -> Unit) {
    val active = index == selectedIndex
    IconButton(onClick = { onClick(index) }) {
        Icon(icon, contentDescription = label, tint = if (active) Color(0xFF2F6D6F) else Color(0xFF7B7F85))
    }
}
