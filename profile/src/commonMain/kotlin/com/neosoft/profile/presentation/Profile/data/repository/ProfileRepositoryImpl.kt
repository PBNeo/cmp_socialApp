package com.neosoft.profile.data.repository

import com.neosoft.profile.data.remote.ProfileApiService
import com.neosoft.profile.domain.entity.FollowerPreview
import com.neosoft.profile.domain.entity.FullProfile
import com.neosoft.profile.domain.entity.UserPost
import com.neosoft.profile.domain.repository.ProfileRepository

class ProfileRepositoryImpl(private val api: ProfileApiService) : ProfileRepository {
    override suspend fun getFullProfile(userId: String): FullProfile {
        val resp = api.getFullProfileMock(userId) // swap to api.getFullProfile(userId) for production

        return FullProfile(
            userId = resp.userId,
            name = resp.name,
            location = resp.location,
            bio = resp.bio,
            avatarUrl = resp.avatarUrl,
            postsCount = resp.postsCount,
            followingCount = resp.followingCount,
            followersCount = resp.followersCount,
            isFollowing = resp.isFollowing,
            followersPreview = resp.followersPreview.map { FollowerPreview(it.userId, it.name, it.avatarUrl) },
            posts = resp.posts.map { UserPost(it.postId, it.imageUrl) }
        )
    }
}
