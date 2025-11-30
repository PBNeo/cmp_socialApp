package com.neosoft.auth.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponse(
    val userId: String,
    val token: String,
    val refreshToken: String
)
