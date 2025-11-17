package com.neosoft.socialapp.app
import SplashScreenRoot
import VerifyOTPViewModel
import VerifyOtpScreenRoot
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.neosoft.auth.presentation.register.LoginScreenRoot
import com.neosoft.auth.presentation.register.RegisterViewModel
import com.neosoft.socialapp.splash.presentation.SplashViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel


@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = Route.Splash.route
        ) {
            composable(Route.Splash.route) {
                val viewModel = koinViewModel<SplashViewModel>()
                SplashScreenRoot(viewModel, {
                    navController.navigate(
                        Route.Login.route
                    )

                })
            }
            composable(Route.Login.route) {
                val viewModel = koinViewModel<RegisterViewModel>()
                LoginScreenRoot (viewModel, {
                    navController.navigate(
                        Route.VerifyOtp.route
                    )
                })
            }

            composable(Route.VerifyOtp.route) {
                val viewModel = koinViewModel<VerifyOTPViewModel>()
                VerifyOtpScreenRoot (viewModel, {

                })
            }
        }

    }
}


@Composable
private inline fun <reified T: ViewModel> NavBackStackEntry.sharedKoinViewModel(
    navController: NavController
): T {
    val navGraphRoute = destination.parent?.route ?: return koinViewModel<T>()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return koinViewModel(
        viewModelStoreOwner = parentEntry
    )
}