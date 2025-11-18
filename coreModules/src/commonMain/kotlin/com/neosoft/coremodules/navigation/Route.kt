package com.neosoft.coremodules.navigation


import kotlinx.serialization.Serializable

sealed interface Route {
    val path: String

    @Serializable data object Splash : Route { override val path = "splash" }
    @Serializable data object Login : Route { override val path = "login" }

    // Generic route with args
    data class WithArgs<T: @Serializable Any>(
        val routeName: String,
        val serializer: kotlinx.serialization.KSerializer<T>
    ) : Route {
        override val path = "$routeName/{args}"
    }
}
