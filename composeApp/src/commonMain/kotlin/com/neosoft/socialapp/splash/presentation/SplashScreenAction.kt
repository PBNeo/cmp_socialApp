package com.neosoft.socialapp.splash.presentation

sealed interface SplashScreenAction {

    object OnContinueClicked: SplashScreenAction
    data class OnPageSwiped(val index: Int): SplashScreenAction

}