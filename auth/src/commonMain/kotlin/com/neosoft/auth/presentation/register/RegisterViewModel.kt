package com.neosoft.auth.presentation.register
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neosoft.auth.domain.usecase.RegisterUserUseCase
import com.neosoft.coremodules.navigation.AppRouter
import com.neosoft.coremodules.navigation.Route
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val registerUser: RegisterUserUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(RegisterScreenState())
    val state: StateFlow<RegisterScreenState> = _state

    fun onAction(action: RegisterScreenAction, router: AppRouter,) {
        print("RegisterScreenAction")
        when (action) {

            is RegisterScreenAction.OnNext -> {
                viewModelScope.launch {
                    println(" debug message")
                    _state.value = state.value.copy(loading = true)

                    try {
                        val user = registerUser(action.mobleNo, action.countryCode)
                        router.go(Route.VerifyOtp(mobile = user.mobileNo))

                    } catch (e: Exception) {
                        println(" $e")
                        _state.value = state.value.copy(
                            loading = false,
                            error = e.message
                        )
                    }
                }
            }

            RegisterScreenAction.OnSignIn -> {}
            RegisterScreenAction.onBackPressed -> {

            }
        }
    }
}
