package com.neosoft.socialapp.di

import NotificationViewModel
import VerifyOTPViewModel
import androidx.room.RoomDatabase
import com.neosoft.auth.data.remote.AuthApiService
import com.neosoft.auth.data.repository.AuthRepositoryImpl
import com.neosoft.auth.domain.repository.AuthRepository
import com.neosoft.auth.domain.usecase.RegisterUserUseCase
import com.neosoft.auth.presentation.register.RegisterViewModel
import com.neosoft.createPost.data.remote.PostApiService
import com.neosoft.createPost.data.repository.PostRepositoryImpl
import com.neosoft.createPost.domain.repository.PostRepository
import com.neosoft.createPost.domain.usecase.CreatePostUseCase
import com.neosoft.createPost.presentation.CreatePostViewModel
import com.neosoft.localStorage.database.AppDatabase
import com.neosoft.localStorage.repository.UserRepository
import com.neosoft.notification.data.remote.NotificationApiService
import com.neosoft.notification.data.repository.NotificationRepositoryImpl
import com.neosoft.notification.domain.repository.NotificationRepository
import com.neosoft.notification.domain.usecase.GetNotificationsUseCase
import com.neosoft.profile.data.remote.ProfileApiService
import com.neosoft.profile.data.repository.ProfileRepositoryImpl
import com.neosoft.profile.domain.repository.ProfileRepository
import com.neosoft.profile.domain.usecase.GetFullProfileUseCase
import com.neosoft.socialapp.core.LocalDataBaseBuilder
import com.neosoft.socialapp.splash.presentation.SplashViewModel
import neosoft.accountUserSetup.AccountUserSetupViewModel
import neosoft.changePassword.ChangePasswordViewModel
import com.neosoft.chat.presentation.details.ChatViewModel
import neosoft.chat.di.chatModule
import neosoft.comment.CommentViewModel
import neosoft.forgotPassword.ForgotPasswordViewModel
import neosoft.home.HomeViewModel
import neosoft.login.LoginViewModel
import neosoft.lounge.di.loungeModule
import neosoft.profile.ProfileViewModel
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

    single { ProfileApiService(get()) }
    single<ProfileRepository> {
        ProfileRepositoryImpl(
            api = get(),
            //  userRepository = get ()
        )
    }

    single<GetFullProfileUseCase> { GetFullProfileUseCase(get()) }

    factory<ProfileViewModel> { ProfileViewModel(get()) }

    single { NotificationApiService(get()) }
    single<NotificationRepository> {
        NotificationRepositoryImpl(
            api = get(),
            //  userRepository = get ()
        )
    }
    single<GetNotificationsUseCase> { GetNotificationsUseCase(get()) }
    factory<NotificationViewModel> { NotificationViewModel(get()) }


    single { PostApiService(get()) }
    single<PostRepository> {
        PostRepositoryImpl(
            api = get(),
            //  userRepository = get ()
        )
    }
    single<CreatePostUseCase> { CreatePostUseCase(get()) }
    factory<CreatePostViewModel> { CreatePostViewModel(get()) }
    chatModule
    loungeModule
}
