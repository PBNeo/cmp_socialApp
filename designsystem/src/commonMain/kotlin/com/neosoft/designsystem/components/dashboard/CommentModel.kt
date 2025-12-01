package com.neosoft.designsystem.components.dashboard

import kotlinx.serialization.Serializable
import androidx.compose.runtime.Immutable

@Immutable
@Serializable
data class CommentModel(
    val id: String,
    val author: String,
    val avatarUrl: String?,
    val timeAgo: String,
    val text: String?,
    val imageUrl: String?,
    val likes: Int,
    val comments: Int
)


