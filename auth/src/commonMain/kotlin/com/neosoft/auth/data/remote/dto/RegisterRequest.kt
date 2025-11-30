package com.neosoft.auth.data.remote.dto


import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequest(
    val mobileNo: String,
    val countryCode: String
)
