package neosoft.notification

import com.neosoft.notification.domain.entity.NotificationItem

data class NotificationUiState(
    val isLoading: Boolean = false,
    val notifications: List<NotificationItem> = emptyList(),
    val error: String? = null
)
