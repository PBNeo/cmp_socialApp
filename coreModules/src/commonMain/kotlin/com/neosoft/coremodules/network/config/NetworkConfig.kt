package com.neosoft.coremodules.network.config

data class NetworkConfig(
    val baseUrl: String,
    val connectTimeoutMillis: Long = 30_000,
    val requestTimeoutMillis: Long = 30_000,
    val socketTimeoutMillis: Long = 30_000,
    val enableLogging: Boolean = true,

    // Optional: Provides token to client
    val tokenProvider: (() -> String?)? = null,

    // Optional: Refresh token handler (401 flow)
    val refreshTokenHandler: (suspend () -> Boolean)? = null
)
