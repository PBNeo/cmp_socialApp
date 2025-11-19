package neosoft.welcome

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class WelcomeViewModel : ViewModel() {
    private val _state = MutableStateFlow(WelcomeScreenState())
    val state = _state
}
