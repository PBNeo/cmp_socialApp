package com.neosoft.coremodules.navigation

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

sealed interface Route {
    val path: String // every route must have a path

    @Serializable
    data object Splash : Route {
        override val path: String = "splash"
    }

    @Serializable
    data object Login : Route {
        override val path: String = "auth/login"
    }

    @Serializable
    data object VerifyOtp : Route {
        override val path: String = "auth/verifyOtp"
    }

    interface WithArgs<T> : Route {
        val routeName: String
        val serializer: KSerializer<T>
        override val path: String
            get() = "$routeName/{args}"
    }

    @Serializable
    data class Profile(val id: String) : WithArgs<Profile> {
        override val path: String = "/Profile"
        override val routeName: String = "profile"
        override val serializer = Profile.serializer()
    }
}
