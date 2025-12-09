package neosoft.lounge.presentation.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.neosoft.lounge.domain.usecase.CreateLoungeUseCase

data class CreateLoungeState(
    val isLoading: Boolean = false,
    val success: Boolean = false,
    val error: String? = null
)

sealed interface CreateLoungeAction {
    data class Create(val title: String, val description: String) : CreateLoungeAction
}

class CreateLoungeViewModel(private val createLoungeUseCase: CreateLoungeUseCase) : ViewModel() {
    private val _state = MutableStateFlow(CreateLoungeState(isLoading = false))
    val state: StateFlow<CreateLoungeState> = _state

    fun onAction(action: CreateLoungeAction) {
        when (action) {
            is CreateLoungeAction.Create -> create(action.title, action.description)
        }
    }

    private fun create(title: String, description: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)
            try {
                val created = createLoungeUseCase(title, description)
                _state.value = CreateLoungeState(isLoading = false, success = true)
            } catch (e: Exception) {
                _state.value = CreateLoungeState(isLoading = false, success = false, error = e.message)
            }
        }
    }
}
