package neosoft.profile

data class FollowerItem(
    val userId: String,
    val name: String,
    val avatarUrl: String?
)

data class PostItem(
    val postId: String,
    val imageUrl: String
)

data class ProfileScreenState(
    val isLoading: Boolean = false,
    val userId: String? = null,
    val name: String? = null,
    val location: String? = null,
    val bio: String? = null,
    val avatarUrl: String? = null,
    val postsCount: Int = 0,
    val followingCount: Int = 0,
    val followersCount: Int = 0,
    val isFollowing: Boolean = false,
    val followersPreview: List<FollowerItem> = emptyList(),
    val posts: List<PostItem> = emptyList(),
    val error: String? = null
)
