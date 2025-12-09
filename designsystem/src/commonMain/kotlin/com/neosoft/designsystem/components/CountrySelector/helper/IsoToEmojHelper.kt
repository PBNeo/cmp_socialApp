package com.neosoft.designsystem.components.CountrySelector.helper
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.text.TextGranularity.Companion.Character

fun isoToEmoji(iso: String): String {
    if (iso.length != 2) return ""

    val base = 0x1F1E6

    fun codePointToSurrogates(codePoint: Int): String {
        val high = ((codePoint - 0x10000) shr 10) + 0xD800
        val low = ((codePoint - 0x10000) and 0x3FF) + 0xDC00
        return Char(high).toString() + Char(low)
    }

    val first = iso[0].uppercaseChar()
    val second = iso[1].uppercaseChar()

    val firstCodePoint = base + (first - 'A')
    val secondCodePoint = base + (second - 'A')

    return codePointToSurrogates(firstCodePoint) +
            codePointToSurrogates(secondCodePoint)
}
