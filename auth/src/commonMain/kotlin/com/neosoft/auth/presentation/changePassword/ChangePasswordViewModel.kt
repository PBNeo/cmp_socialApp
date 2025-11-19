package neosoft.changePassword

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class ChangePasswordViewModel : ViewModel() {
    private val _state = MutableStateFlow(ChangePasswordScreenState())
    val state = _state
}
