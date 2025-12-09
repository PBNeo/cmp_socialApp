package com.neosoft.notification.data.remote

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.delay
import kotlinx.serialization.Serializable
import kotlin.time.Clock

@Serializable
data class NotificationDto(
    val id: String,
    val title: String,
    val message: String,
    val timestamp: Long,
    val avatarUrl: String? = null,
    val type: String? = null // follow/like/comment etc
)

@Serializable
data class NotificationsResponse(
    val notifications: List<NotificationDto>
)

class NotificationApiService(private val client: HttpClient) {
    suspend fun getNotifications(userId: String): NotificationsResponse {
        return client.get("users/$userId/notifications").body()
    }

    // Mock for UI/dev
    suspend fun getNotificationsMock(userId: String): NotificationsResponse {
        delay(200)
        val now =1001L
        val list = listOf(
            NotificationDto(id="n1", title="Patrick Followed you", message="Just Now", timestamp=now-1000L*30, avatarUrl=null, type="follow"),
            NotificationDto(id="n2", title="Chris Followed you", message="2mins ago", timestamp=now-1000L*120, avatarUrl=null, type="follow"),
            NotificationDto(id="n3", title="Segun Liked your photo", message="15mins ago", timestamp=now-1000L*900, avatarUrl=null, type="like"),
            NotificationDto(id="n4", title="Chris commented on your post", message="1hour ago", timestamp=now-1000L*3600, avatarUrl=null, type="comment")
        )
        return NotificationsResponse(notifications = list)
    }
}
