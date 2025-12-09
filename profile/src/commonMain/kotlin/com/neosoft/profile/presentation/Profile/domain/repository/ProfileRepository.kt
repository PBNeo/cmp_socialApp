package com.neosoft.profile.domain.repository

import com.neosoft.profile.domain.entity.FullProfile

interface ProfileRepository {
    suspend fun getFullProfile(userId: String): FullProfile
}
