package com.neosoft.notification.domain.entity

data class NotificationItem(
    val id: String,
    val title: String,
    val message: String,
    val timestamp: Long,
    val avatarUrl: String? = null,
    val type: String = ""
)
