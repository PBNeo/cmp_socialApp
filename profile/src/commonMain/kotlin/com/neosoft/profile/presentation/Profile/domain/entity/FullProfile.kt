package com.neosoft.profile.domain.entity

data class FullProfile(
    val userId: String,
    val name: String,
    val location: String? = null,
    val bio: String? = null,
    val avatarUrl: String? = null,
    val postsCount: Int = 0,
    val followingCount: Int = 0,
    val followersCount: Int = 0,
    val isFollowing: Boolean = false,
    val followersPreview: List<FollowerPreview> = emptyList(),
    val posts: List<UserPost> = emptyList()
)

data class FollowerPreview(
    val userId: String,
    val name: String,
    val avatarUrl: String? = null
)

data class UserPost(
    val postId: String,
    val imageUrl: String
)
