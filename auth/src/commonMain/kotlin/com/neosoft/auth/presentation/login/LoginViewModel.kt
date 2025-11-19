package neosoft.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class LoginViewModel : ViewModel() {
    private val _state = MutableStateFlow(LoginScreenState())
    val state = _state
}
