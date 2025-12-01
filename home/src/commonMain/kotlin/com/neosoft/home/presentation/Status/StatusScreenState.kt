package neosoft.status

data class StatusScreenState
    (
    val isLoading: Boolean = true,
    val mediaUrl: String = "",
    val isVideo: Boolean = true,
    val avatarUrl: String = "",
    val author: String = "",
    val timeAgo: String = "",
    val viewersCount: Int = 0,
    val isLive: Boolean = true
)


