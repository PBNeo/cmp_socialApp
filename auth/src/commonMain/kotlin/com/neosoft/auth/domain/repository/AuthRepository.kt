package com.neosoft.auth.domain.repository

import com.neosoft.auth.domain.entity.User

interface AuthRepository {
    suspend fun registerUser(mobileNo: String, countryCode: String): User
}
