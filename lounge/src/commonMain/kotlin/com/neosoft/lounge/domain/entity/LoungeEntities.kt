package com.neosoft.lounge.domain.entity

data class Lounge(
    val id: String,
    val title: String,
    val description: String,
    val listenerCount: Int,
    val hostId: String,
    val hostName: String,
    val coverUrl: String? = null
)

data class Participant(
    val userId: String,
    val name: String,
    val avatarUrl: String? = null,
    val role: String = "listener"
)

data class LoungeDetails(
    val id: String,
    val title: String,
    val description: String,
    val hostId: String,
    val hostName: String,
    val participants: List<Participant>
)
