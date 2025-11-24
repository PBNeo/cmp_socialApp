package com.neosoft.socialapp.di

import VerifyOTPViewModel
import com.neosoft.auth.presentation.register.RegisterViewModel
import com.neosoft.socialapp.splash.presentation.SplashViewModel
import neosoft.accountUserSetup.AccountUserSetupViewModel
import neosoft.changePassword.ChangePasswordViewModel
import neosoft.forgotPassword.ForgotPasswordViewModel
import neosoft.login.LoginViewModel
import neosoft.profileSetup.ProfileSetupViewModel
import neosoft.welcome.WelcomeViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {

    viewModelOf(::SplashViewModel)
    viewModelOf(::RegisterViewModel)
    viewModelOf(::LoginViewModel)
    viewModelOf(::VerifyOTPViewModel)
    viewModelOf(::ProfileSetupViewModel)
    viewModelOf(::AccountUserSetupViewModel)
    viewModelOf(::ForgotPasswordViewModel)
    viewModelOf(::ChangePasswordViewModel)
    viewModelOf(::WelcomeViewModel)

}