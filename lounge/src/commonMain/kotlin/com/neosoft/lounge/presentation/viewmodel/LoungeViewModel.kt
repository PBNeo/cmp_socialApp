package neosoft.lounge.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.neosoft.lounge.domain.entity.LoungeDetails
import com.neosoft.lounge.domain.usecase.GetLoungeDetailsUseCase

data class LoungeState(
    val isLoading: Boolean = false,
    val details: LoungeDetails? = null,
    val error: String? = null
)

sealed interface LoungeAction {
    object Join : LoungeAction
    object Leave : LoungeAction
    object Stop : LoungeAction
}

class LoungeViewModel(private val getLoungeDetailsUseCase: GetLoungeDetailsUseCase) : ViewModel() {

    private val _state = MutableStateFlow(LoungeState(isLoading = true))
    val state: StateFlow<LoungeState> = _state

    fun load(loungeId: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)
            try {
                val details = getLoungeDetailsUseCase(loungeId)
                _state.value = LoungeState(isLoading = false, details = details)
            } catch (e: Exception) {
                _state.value = LoungeState(isLoading = false, details = null, error = e.message)
            }
        }
    }

    fun onAction(action: LoungeAction) {
        when (action) {
            LoungeAction.Join -> { /* call use-case or ws */ }
            LoungeAction.Leave -> { /* call use-case or ws */ }
            LoungeAction.Stop -> { /* host stop */ }
        }
    }
}
