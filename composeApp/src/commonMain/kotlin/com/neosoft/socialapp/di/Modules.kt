package com.neosoft.socialapp.di

import VerifyOTPViewModel
import androidx.room.RoomDatabase
import com.neosoft.auth.data.remote.AuthApiService
import com.neosoft.auth.data.repository.AuthRepositoryImpl
import com.neosoft.auth.domain.repository.AuthRepository
import com.neosoft.auth.domain.usecase.RegisterUserUseCase
import com.neosoft.auth.presentation.register.RegisterViewModel
import com.neosoft.localStorage.database.AppDatabase
import com.neosoft.localStorage.repository.UserRepository
import com.neosoft.socialapp.core.LocalDataBaseBuilder
import com.neosoft.socialapp.splash.presentation.SplashViewModel
import neosoft.accountUserSetup.AccountUserSetupViewModel
import neosoft.changePassword.ChangePasswordViewModel
import neosoft.comment.CommentViewModel
import neosoft.forgotPassword.ForgotPasswordViewModel
import neosoft.home.HomeViewModel
import neosoft.login.LoginViewModel
import neosoft.profileSetup.ProfileSetupViewModel
import neosoft.status.StatusViewModel
import neosoft.welcome.WelcomeViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {

    viewModelOf(::SplashViewModel)
    viewModelOf(::LoginViewModel)
    viewModelOf(::VerifyOTPViewModel)
    viewModelOf(::ProfileSetupViewModel)
    viewModelOf(::AccountUserSetupViewModel)
    viewModelOf(::ForgotPasswordViewModel)
    viewModelOf(::ChangePasswordViewModel)
    viewModelOf(::WelcomeViewModel)
    viewModelOf(::HomeViewModel)
    viewModelOf(::StatusViewModel)
    viewModelOf(::CommentViewModel)


    single <RoomDatabase.Builder<AppDatabase>>{
        LocalDataBaseBuilder.getDatabaseBuilder()
    }     // expect/actual database provider
    single { get<RoomDatabase.Builder<AppDatabase>>().build() }
    single { UserRepository(get()) }
    single { AuthApiService(get()) }

    single<AuthRepository> {
        AuthRepositoryImpl(
            api = get(),
          //  userRepository = get ()
        )
    }

    single<RegisterUserUseCase> { RegisterUserUseCase(get()) }

    factory<RegisterViewModel> { RegisterViewModel(get()) }


}
