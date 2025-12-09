package com.neosoft.lounge.domain.repository

import com.neosoft.lounge.domain.entity.Lounge
import com.neosoft.lounge.domain.entity.LoungeDetails

interface LoungeRepository {
    suspend fun getLounges(): List<Lounge>
    suspend fun createLounge(title: String, description: String): Lounge
    suspend fun getLoungeDetails(loungeId: String): LoungeDetails
}
