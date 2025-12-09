package com.neosoft.socialapp.app
import SplashScreenRoot
import StatusScreenRoot
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
import com.neosoft.designsystem.components.dashboard.CommentModel
import com.neosoft.notification.presentation.NotificationScreenRoot
import com.neosoft.socialapp.com.neosoft.socialapp.main.MainScreenRoot
import com.neosoft.socialapp.splash.presentation.SplashViewModel
import io.ktor.http.Url
import kotlinx.serialization.json.Json
import neosoft.accountUserSetup.AccountUserSetupViewModel
import neosoft.changePassword.ChangePasswordScreenRoot
import neosoft.changePassword.ChangePasswordViewModel
import neosoft.comment.CommentScreenRoot
import neosoft.forgotPassword.ForgotPasswordViewModel
import neosoft.home.HomeScreenRoot
import neosoft.home.HomeViewModel
import neosoft.login.LoginViewModel
import neosoft.profileSetup.ProfileSetupViewModel
import neosoft.status.StatusViewModel
import neosoft.welcome.WelcomeScreen
import neosoft.welcome.WelcomeScreenRoot
import neosoft.welcome.WelcomeViewModel
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
            startDestination = Route.Root.path
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
                val routeString = backStackEntry.destination.route ?: ""
                val mobileEncoded = routeString
                    .substringAfter("mobile=", "1") // "defaultMobile" is used if "mobile" is missing


                VerifyOtpScreenRoot(mobile = mobileEncoded,)
            }

            composable(Route.ProfileSetup.path) {
                val viewModel = koinViewModel<ProfileSetupViewModel>()
                ProfileSetupScreenRoot(viewModel)
            }

            composable(Route.WelcomeScreen.path) {
                val viewModel = koinViewModel<WelcomeViewModel>()
                WelcomeScreenRoot(viewModel)
            }

            composable(Route.Root.path) {
                MainScreenRoot()
            }


            composable(Route.Home.path) {
                val viewModel = koinViewModel<HomeViewModel>()
                HomeScreenRoot(viewModel)
            }
            composable(Route.Notification.path) {
                NotificationScreenRoot("1")
            }
            composable(
                route = "home/status/{postId}",
                arguments = listOf(navArgument("postId") { type = NavType.StringType })
            ) { backStackEntry ->
                val routeString = backStackEntry.destination.route ?: ""
                val postEncoded = routeString
                    .substringAfter("postId=", "1") //
                StatusScreenRoot(postId = postEncoded,)
            }


//            composable(
//                route = "home/comments/{comments}",
//                arguments = listOf(navArgument("comments") { type = NavType.StringType })
//            ) { backStackEntry ->
//                val commentsJson = backStackEntry.arguments?.getString("comments") ?: "[]"
//                val comments: List<CommentModel> = Json.decodeFromString(commentsJson)
//
//                CommentScreenRoot(comments = comments)
//            }
            composable(
                route = "home/comments/{args}",
                arguments = listOf(navArgument("args") { type = NavType.StringType })
            ) { backStackEntry ->
                // Safe extraction of arguments
                val args = router.getArgs(backStackEntry, Route.CommentsRoute)
                    ?: Route.CommentsArgs(emptyList()) // fallback empty list

                CommentScreenRoot(comments = args.comments)
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




