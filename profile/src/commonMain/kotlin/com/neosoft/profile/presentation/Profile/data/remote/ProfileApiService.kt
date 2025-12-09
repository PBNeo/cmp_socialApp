package com.neosoft.profile.data.remote

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.delay
import kotlinx.serialization.Serializable

@Serializable
data class FollowerPreviewDto(
    val userId: String,
    val name: String,
    val avatarUrl: String? = null
)

@Serializable
data class UserPostDto(
    val postId: String,
    val imageUrl: String
)

@Serializable
data class FullProfileResponse(
    val userId: String,
    val name: String,
    val location: String? = null,
    val bio: String? = null,
    val avatarUrl: String? = null,
    val postsCount: Int = 0,
    val followingCount: Int = 0,
    val followersCount: Int = 0,
    val isFollowing: Boolean = false,
    val followersPreview: List<FollowerPreviewDto> = emptyList(),
    val posts: List<UserPostDto> = emptyList()
)

class ProfileApiService(private val client: HttpClient) {
    suspend fun getFullProfile(userId: String): FullProfileResponse {
        return client.get("profiles/$userId/full").body()
    }

    // Mock for UI/dev
    suspend fun getFullProfileMock(userId: String): FullProfileResponse {
        delay(250)
        return FullProfileResponse(
            userId = userId,
            name = "Oyin Dolapo",
            location = "Abeokuta, Ogun",
            bio = "I’m a positive person. I love to travel and eat. Always available for chat",
            avatarUrl = null,
            postsCount = 87,
            followingCount = 870,
            followersCount = 15000,
            isFollowing = false,
            followersPreview = listOf(
                FollowerPreviewDto("1","Elijah", null),
                FollowerPreviewDto("2","Abdul", null),
                FollowerPreviewDto("3","Qudus", null),
            ),
            posts = List(9) { i -> UserPostDto(postId = "p$i", imageUrl = "https://picsum.photos/id/${100 + i}/200/200") }
        )
    }
}
