package com.neosoft.socialapp.di

import VerifyOTPViewModel
import com.neosoft.auth.presentation.register.RegisterViewModel
import com.neosoft.socialapp.splash.presentation.SplashViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {

    viewModelOf(::SplashViewModel)
    viewModelOf(::RegisterViewModel)
    viewModelOf(::VerifyOTPViewModel)
}