package com.neosoft.designsystem.components.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun StoryComposerPlaceholder(modifier: Modifier = Modifier) {
    Box(modifier = modifier.size(220.dp).clip(RoundedCornerShape(16.dp)).background(androidx.compose.ui.graphics.Color(0xFFF6F8F8)), contentAlignment = Alignment.Center) {
        Icon(Icons.Default.CameraAlt, contentDescription = "Camera", modifier = Modifier.size(64.dp))
    }
}
