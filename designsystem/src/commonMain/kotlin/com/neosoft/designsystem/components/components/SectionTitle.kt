package com.example.designsystem.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun SectionTitle(text: String, modifier: Modifier = Modifier) {
    Text(text = text, color = androidx.compose.ui.graphics.Color(0xFF7B7F85), fontWeight = androidx.compose.ui.text.font.FontWeight.SemiBold, fontSize = 13.sp, modifier = modifier)
}
