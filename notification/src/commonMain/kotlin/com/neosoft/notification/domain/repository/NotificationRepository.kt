package com.neosoft.notification.domain.repository

import com.neosoft.notification.domain.entity.NotificationItem

interface NotificationRepository {
    suspend fun getNotifications(userId: String): List<NotificationItem>
}
