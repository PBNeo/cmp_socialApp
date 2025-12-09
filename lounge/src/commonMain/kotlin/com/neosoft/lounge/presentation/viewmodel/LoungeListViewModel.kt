package neosoft.lounge.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neosoft.lounge.domain.entity.Lounge
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.neosoft.lounge.domain.usecase.GetLoungesUseCase

data class LoungeListState(
    val isLoading: Boolean = false,
    val lounges: List<Lounge> = emptyList(),
    val error: String? = null
)

sealed interface LoungeListAction {
    object Refresh : LoungeListAction
    data class OpenLounge(val loungeId: String) : LoungeListAction
    object Create : LoungeListAction
}

class LoungeListViewModel(private val getLoungesUseCase: GetLoungesUseCase) : ViewModel() {
    private val _state = MutableStateFlow(LoungeListState(isLoading = true))
    val state: StateFlow<LoungeListState> = _state

    init { load() }

    fun load() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)
            try {
                val list = getLoungesUseCase()
                _state.value = LoungeListState(isLoading = false, lounges = list)
            } catch (e: Exception) {
                _state.value = LoungeListState(isLoading = false, lounges = emptyList(), error = e.message)
            }
        }
    }

    fun onAction(action: LoungeListAction): String? {
        return when (action) {
            LoungeListAction.Refresh -> { load(); null }
            is LoungeListAction.OpenLounge -> "lounge/${action.loungeId}"
            LoungeListAction.Create -> "create_lounge"
        }
    }
}
