package com.neosoft.auth.domain.entity

data class User(
    val userId: String,
    val mobileNo: String,
    val countryCode: String,
    val token: String,
    val refreshToken: String
)