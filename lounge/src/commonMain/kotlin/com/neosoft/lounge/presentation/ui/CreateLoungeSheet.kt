package com.neosoft.lounge.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CreateLoungeSheet(onCreate: (String, String) -> Unit, onClose: () -> Unit) {
    val title = remember { mutableStateOf("") }
    val desc = remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Text(text = "Create a lounge", modifier = Modifier.padding(bottom = 12.dp))
        TextField(value = title.value, onValueChange = { title.value = it }, modifier = Modifier.fillMaxWidth(), placeholder = { Text("Name") })
        Spacer(modifier = Modifier.height(12.dp))
        TextField(value = desc.value, onValueChange = { desc.value = it }, modifier = Modifier.fillMaxWidth(), placeholder = { Text("Description") })
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { onCreate(title.value, desc.value) }, modifier = Modifier.fillMaxWidth()) {
            Text("Create")
        }
    }
}
