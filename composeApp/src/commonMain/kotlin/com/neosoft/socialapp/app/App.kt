package com.neosoft.socialapp.app
import SplashScreenRoot
import VerifyOtpScreenRoot
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.neosoft.auth.presentation.accountUsersetup.AccountUserSetupScreen
import com.neosoft.auth.presentation.accountUsersetup.AccountUserSetupScreenRoot
import com.neosoft.auth.presentation.forgotPassword.ForgotPasswordScreen
import com.neosoft.auth.presentation.forgotPassword.ForgotPasswordScreenRoot
import com.neosoft.auth.presentation.login.LoginScreenRoot
import com.neosoft.auth.presentation.profileSetup.ProfileSetupScreenRoot
import com.neosoft.auth.presentation.register.RegisterScreenRoot
import com.neosoft.auth.presentation.register.RegisterViewModel
import com.neosoft.coremodules.navigation.AppRouter
import com.neosoft.coremodules.navigation.LocalRouter
import com.neosoft.coremodules.navigation.Route
import com.neosoft.socialapp.splash.presentation.SplashViewModel
import neosoft.accountUserSetup.AccountUserSetupViewModel
import neosoft.changePassword.ChangePasswordScreenRoot
import neosoft.changePassword.ChangePasswordViewModel
import neosoft.forgotPassword.ForgotPasswordViewModel
import neosoft.login.LoginViewModel
import neosoft.profileSetup.ProfileSetupViewModel
import org.koin.compose.getKoin
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.Koin


@Composable
fun App() {
    val navController = rememberNavController()
    val router = remember { AppRouter(navController) }
    val koin = getKoin()

    CompositionLocalProvider(LocalRouter provides router) {

        NavHost(
            navController = navController,
            startDestination = Route.Splash.path
        ) {
            composable(Route.Splash.path) {
                val viewModel = koinViewModel<SplashViewModel>()
                SplashScreenRoot(viewModel) {
                    router.go(Route.Login) // navigate using AppRouter
                }
            }

            composable(Route.Register.path) {
                val viewModel = koin.get<RegisterViewModel>()
                RegisterScreenRoot(viewModel) {
                    router.go(Route.VerifyOtp) // navigate using AppRouter
                }
            }

            composable(Route.Login.path) {
                val viewModel = koinViewModel<LoginViewModel>()
                LoginScreenRoot(viewModel)
            }

            composable(Route.ForgotPassword.path) {
                val viewModel = koinViewModel<ForgotPasswordViewModel>()
                ForgotPasswordScreenRoot(viewModel)
            }

            composable(Route.ChangePassword.path) {
                val viewModel = koinViewModel<ChangePasswordViewModel>()
                ChangePasswordScreenRoot(viewModel)
            }

            composable(Route.AccountSetup.path) {
                val viewModel = koinViewModel<AccountUserSetupViewModel>()
                AccountUserSetupScreenRoot(viewModel)
            }

            composable(
                route = "auth/verifyOtp/{mobile}",
                arguments = listOf(navArgument("mobile") { type = NavType.StringType })
            ) { backStackEntry ->
                val mobileEncoded = backStackEntry.arguments?.getString("mobile")!!
                VerifyOtpScreenRoot(mobile = mobileEncoded,)
            }

            composable(Route.ProfileSetup.path) {
                val viewModel = koinViewModel<ProfileSetupViewModel>()
                ProfileSetupScreenRoot(viewModel)
            }


            // Example: route with args
//            composable(
//                Route.Profile.path,
//                arguments = listOf(navArgument("args") { type = NavType.StringType })
//            ) { backStack ->
//                val profile = router.getArgs<Route.Profile.Profile>(backStack, Route.Profile)
//                ProfileScreen(profile)
//            }

        }
    }
}




