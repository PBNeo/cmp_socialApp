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
}
