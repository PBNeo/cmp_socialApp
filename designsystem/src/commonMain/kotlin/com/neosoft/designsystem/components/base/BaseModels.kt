package com.example.designsystem.base

sealed interface UiState

data class BaseState<T>(
    val loading: Boolean = false,
    val error: String? = null,
    val data: T? = null
) : UiState

sealed interface UiAction

sealed interface ScreenAction : UiAction {
    object OnBack : ScreenAction
    object OnNext : ScreenAction
}
