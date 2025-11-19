package neosoft.profileSetup

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class ProfileSetupViewModel : ViewModel() {
    private val _state = MutableStateFlow(ProfileSetupScreenState())
    val state = _state
}
