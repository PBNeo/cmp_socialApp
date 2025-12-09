package com.neosoft.lounge.domain.usecase

import com.neosoft.lounge.domain.entity.Lounge
import com.neosoft.lounge.domain.entity.LoungeDetails
import com.neosoft.lounge.domain.repository.LoungeRepository

class GetLoungesUseCase(private val repository: LoungeRepository) {
    suspend operator fun invoke(): List<Lounge> = repository.getLounges()
}

class CreateLoungeUseCase(private val repository: LoungeRepository) {
    suspend operator fun invoke(title: String, description: String): Lounge = repository.createLounge(title, description)
}

class GetLoungeDetailsUseCase(private val repository: LoungeRepository) {
    suspend operator fun invoke(loungeId: String): LoungeDetails = repository.getLoungeDetails(loungeId)
}
