package neosoft.login

interface LoginScreenAction {
    object OnBackPressed : LoginScreenAction
    object OnNext : LoginScreenAction
    object OnForgotPassword : LoginScreenAction
    object OnSignUpClicked : LoginScreenAction
}
