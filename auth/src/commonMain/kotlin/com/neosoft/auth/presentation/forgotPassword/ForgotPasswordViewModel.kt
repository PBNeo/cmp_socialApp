package neosoft.forgotPassword

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class ForgotPasswordViewModel : ViewModel() {
    private val _state = MutableStateFlow(ForgotPasswordScreenState())
    val state = _state
}
