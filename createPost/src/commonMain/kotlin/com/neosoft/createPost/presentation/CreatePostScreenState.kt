package com.neosoft.createPost.presentation

data class CreatePostUiState(
    val isLoading: Boolean = false,
    val success: Boolean = false,
    val error: String? = null
)
