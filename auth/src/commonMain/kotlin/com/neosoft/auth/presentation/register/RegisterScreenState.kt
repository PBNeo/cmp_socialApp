package com.neosoft.auth.presentation.register

data class RegisterScreenState(
    val mobileNo: String = "",
    val countryCode: String = "",
    val loading: Boolean = false,
    val error: String? = null
)



