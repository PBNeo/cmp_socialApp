package com.neosoft.socialapp.app

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object Splash : Route {
        const val route: String = "splash"
    }

    // Example: other routes
    @Serializable
    data object Home : Route {
        const val route: String = "home"
    }

    @Serializable
    data object Login : Route {
        const val route: String = "auth/login"
    }

    @Serializable
    data object VerifyOtp : Route {
        const val route: String = "auth/verifyOtp"
    }
}
