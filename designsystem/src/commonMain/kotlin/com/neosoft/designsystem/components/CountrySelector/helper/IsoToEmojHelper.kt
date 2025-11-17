package com.neosoft.designsystem.components.CountrySelector.helper
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*

fun isoToEmoji(iso: String): String {
    // Guard
    if (iso.length != 2) return ""
    val upper = iso.uppercase()
    // Regional indicator symbol letter A starts at 0x1F1E6
    val first = Character.codePointAt(upper, 0) - 'A'.code + 0x1F1E6
    val second = Character.codePointAt(upper, 1) - 'A'.code + 0x1F1E6
    return String(Character.toChars(first)) + String(Character.toChars(second))
}