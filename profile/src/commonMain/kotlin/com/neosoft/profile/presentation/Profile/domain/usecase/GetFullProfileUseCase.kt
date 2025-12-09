package com.neosoft.profile.domain.usecase

import com.neosoft.profile.domain.entity.FullProfile
import com.neosoft.profile.domain.repository.ProfileRepository

class GetFullProfileUseCase(private val repository: ProfileRepository) {
    suspend operator fun invoke(userId: String): FullProfile {
        return repository.getFullProfile(userId)
    }
}
