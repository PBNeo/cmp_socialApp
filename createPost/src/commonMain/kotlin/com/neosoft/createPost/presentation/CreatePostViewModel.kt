package com.neosoft.createPost.presentation


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.neosoft.createPost.domain.usecase.CreatePostUseCase


class CreatePostViewModel(private val createPostUseCase: CreatePostUseCase) : ViewModel() {
    private val _state = MutableStateFlow(CreatePostUiState(isLoading = false))
    val state: StateFlow<CreatePostUiState> = _state

    fun create(authorId: String, content: String, imageUrls: List<String>, hashtags: List<String>) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)
            try {
                val res = createPostUseCase(authorId, content, imageUrls, hashtags)
                _state.value = CreatePostUiState(isLoading = false, success = res.success, error = res.message)
            } catch (e: Exception) {
                _state.value = CreatePostUiState(isLoading = false, success = false, error = e.message)
            }
        }
    }
}
