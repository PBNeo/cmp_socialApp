package com.neosoft.designsystem.components

import androidx.compose.runtime.Immutable
import com.neosoft.designsystem.components.dashboard.CommentModel

@Immutable
data class PostModel(
    val id: String,
    val author: String,
    val avatarUrl: String?,
    val timeAgo: String,
    val text: String?,
    val imageUrl: String?,
    val likes: Int,
    val comments: Int,
    var commentsList: List<CommentModel>? = null // optional
)
