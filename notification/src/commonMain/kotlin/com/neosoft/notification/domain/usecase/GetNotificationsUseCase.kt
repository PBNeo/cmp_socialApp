package com.neosoft.notification.domain.usecase

import com.neosoft.notification.domain.entity.NotificationItem
import com.neosoft.notification.domain.repository.NotificationRepository

class GetNotificationsUseCase(private val repository: NotificationRepository) {
    suspend operator fun invoke(userId: String): List<NotificationItem> {
        return repository.getNotifications(userId)
    }
}
