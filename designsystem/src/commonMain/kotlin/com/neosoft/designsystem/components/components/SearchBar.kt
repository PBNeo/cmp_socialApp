package com.example.designsystem.components

import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SearchBar(value: String, onValueChange: (String) -> Unit, hint: String = "Type something...", modifier: Modifier = Modifier) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(hint, fontSize = 13.sp) },
        singleLine = true,
        modifier = modifier.height(44.dp),
        shape = RoundedCornerShape(10.dp),
        colors = TextFieldDefaults.colors(
//            containerColor = androidx.compose.ui.graphics.Color(0xFFF8FAFB),
//            focusedBorderColor = androidx.compose.ui.graphics.Color(0xFF2F6D6F),
//            unfocusedBorderColor = androidx.compose.ui.graphics.Color(0xFFE6ECEC),
            cursorColor = androidx.compose.ui.graphics.Color(0xFF2F6D6F)
        ),
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) }
    )
}
