package com.neosoft.auth.presentation.register

interface RegisterScreenAction {


    object onBackPressed: RegisterScreenAction

    object  OnPhoneChange : RegisterScreenAction

    object  OnCountryChange: RegisterScreenAction

    object  OnSignIn: RegisterScreenAction

    object   OnNext: RegisterScreenAction
}