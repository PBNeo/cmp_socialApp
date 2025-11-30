package com.neosoft.auth.domain.usecase

import com.neosoft.auth.domain.entity.User
import com.neosoft.auth.domain.repository.AuthRepository

class RegisterUserUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(mobileNo: String, countryCode: String): User {
        return repository.registerUser(mobileNo, countryCode)
    }
}
