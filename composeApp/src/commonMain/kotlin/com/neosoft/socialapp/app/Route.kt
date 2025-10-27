package com.plcoding.bookpedia.app

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object Splash: Route


}