package com.neosoft.socialapp.di
import SplashViewModel
import com.neosoft.socialapp.core.data.HttpClientFactory
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    single { HttpClientFactory.create(get()) }

    viewModelOf(::SplashViewModel)
}