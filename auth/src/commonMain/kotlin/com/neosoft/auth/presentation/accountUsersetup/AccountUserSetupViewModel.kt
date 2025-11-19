package neosoft.accountUserSetup

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class AccountUserSetupViewModel : ViewModel() {
    private val _state = MutableStateFlow(AccountUserSetupScreenState())
    val state = _state
}
