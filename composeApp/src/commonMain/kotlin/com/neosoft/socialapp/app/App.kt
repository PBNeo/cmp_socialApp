package com.neosoft.socialapp.app
import SplashScreenRoot
import VerifyOTPViewModel
import VerifyOtpScreenRoot
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.neosoft.auth.presentation.register.LoginScreenRoot
import com.neosoft.auth.presentation.register.RegisterViewModel
import com.neosoft.coremodules.navigation.AppRouter
import com.neosoft.coremodules.navigation.LocalRouter
import com.neosoft.coremodules.navigation.Route
import com.neosoft.socialapp.splash.presentation.SplashViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun App() {
    val navController = rememberNavController()
    val router = remember { AppRouter(navController) }

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

            composable(Route.Login.path) {
                val viewModel = koinViewModel<RegisterViewModel>()
                LoginScreenRoot(viewModel) {
                    router.go(Route.VerifyOtp) // navigate using AppRouter
                }
            }

            composable(Route.VerifyOtp.path) {
                val viewModel = koinViewModel<VerifyOTPViewModel>()
                VerifyOtpScreenRoot(viewModel) {
                    router.pop() // go back
                }
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




