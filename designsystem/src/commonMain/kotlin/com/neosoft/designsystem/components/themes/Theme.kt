package com.example.designsystem.themes

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = androidx.compose.ui.graphics.Color(0xFF2F6D6F),
    onPrimary = androidx.compose.ui.graphics.Color.White,
    surface = androidx.compose.ui.graphics.Color.White,
    onSurface = androidx.compose.ui.graphics.Color.Black,
    secondary = androidx.compose.ui.graphics.Color(0xFF7B7F85)
)

@Composable
fun DesignSystemTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = LightColors) {
        content()
    }
}
