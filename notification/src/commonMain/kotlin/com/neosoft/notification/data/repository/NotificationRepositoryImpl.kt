package com.neosoft.notification.data.repository

import com.neosoft.notification.data.remote.NotificationApiService
import com.neosoft.notification.domain.entity.NotificationItem
import com.neosoft.notification.domain.repository.NotificationRepository

class NotificationRepositoryImpl(private val api: NotificationApiService) : NotificationRepository {
    override suspend fun getNotifications(userId: String): List<NotificationItem> {
        val resp = api.getNotificationsMock(userId) // swap to api.getNotifications(userId)
        return resp.notifications.map {
            NotificationItem(
                id = it.id,
                title = it.title,
                message = it.message,
                timestamp = it.timestamp,
                avatarUrl = it.avatarUrl,
                type = it.type ?: ""
            )
        }
    }
}
