import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neosoft.notification.domain.usecase.GetNotificationsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import neosoft.notification.NotificationUiState


class NotificationViewModel(private val getNotificationsUseCase: GetNotificationsUseCase) : ViewModel() {
    private val _state = MutableStateFlow(NotificationUiState(isLoading = true))
    val state: StateFlow<NotificationUiState> = _state

    fun load(userId: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)
            try {
                val list = getNotificationsUseCase(userId)
                _state.value = NotificationUiState(isLoading = false, notifications = list)
            } catch (e: Exception) {
                _state.value = NotificationUiState(isLoading = false, error = e.message)
            }
        }
    }
}
