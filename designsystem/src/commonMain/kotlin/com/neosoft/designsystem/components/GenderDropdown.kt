package com.neosoft.designsystem.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GenderDropdown(
    selectedGender: String,
    onGenderSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        Text("Gender", fontSize = 14.sp, color = Color(0xFF4E4E4E))
        Spacer(Modifier.height(6.dp))

        Box {
            OutlinedTextField(
                value = selectedGender,
                onValueChange = {},
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .clickable { expanded = true },
                shape = RoundedCornerShape(12.dp),
                trailingIcon = {
                   // Icon(Icon.Fi.ArrowDropDown, contentDescription = null)
                }
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                listOf("Male", "Female", "Other").forEach {
                    DropdownMenuItem(
                        text = { Text(it) },
                        onClick = {
                            onGenderSelected(it)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

