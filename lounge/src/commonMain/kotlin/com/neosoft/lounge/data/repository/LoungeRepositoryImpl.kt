package com.neosoft.lounge.data.repository

import com.neosoft.lounge.data.remote.LoungeApiService
import com.neosoft.lounge.domain.entity.Lounge
import com.neosoft.lounge.domain.entity.LoungeDetails
import com.neosoft.lounge.domain.entity.Participant
import com.neosoft.lounge.domain.repository.LoungeRepository

class LoungeRepositoryImpl(private val api: LoungeApiService) : LoungeRepository {
    override suspend fun getLounges(): List<Lounge> {
        return api.getLounges().map {
            Lounge(id = it.id, title = it.title, description = it.description, listenerCount = it.listenerCount, hostId = it.hostId, hostName = it.hostName, coverUrl = it.coverUrl)
        }
    }

    override suspend fun createLounge(title: String, description: String) : Lounge {
        val d = api.createLounge(title, description)
        return Lounge(id = d.id, title = d.title, description = d.description, listenerCount = d.listenerCount, hostId = d.hostId, hostName = d.hostName, coverUrl = d.coverUrl)
    }

    override suspend fun getLoungeDetails(loungeId: String): LoungeDetails {
        val d = api.getLoungeDetails(loungeId)
        return LoungeDetails(
            id = d.id,
            title = d.title,
            description = d.description,
            hostId = d.hostId,
            hostName = d.hostName,
            participants = d.participants.map { Participant(userId = it.userId, name = it.name, avatarUrl = it.avatarUrl, role = it.role) }
        )
    }
}
