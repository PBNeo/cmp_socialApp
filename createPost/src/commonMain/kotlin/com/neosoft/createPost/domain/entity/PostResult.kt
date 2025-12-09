package com.neosoft.createPost.domain.entity

data class PostResult(
    val success: Boolean,
    val postId: String? = null,
    val message: String? = null
)
