package com.neosoft.socialapp.splash.presentation
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class  SplashViewModel () : ViewModel(){
    private val _state = MutableStateFlow(SplashState())
    val state = _state


    fun onAction(action: SplashScreenAction) {
        when (action) {
            is SplashScreenAction.OnPageSwiped -> {
                _state.value.copy(selectedStep = action.index)
            }
            SplashScreenAction.OnContinueClicked -> {

            }
        }
    }
}