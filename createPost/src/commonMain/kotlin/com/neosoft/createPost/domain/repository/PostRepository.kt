package com.neosoft.createPost.domain.repository

import com.neosoft.createPost.domain.entity.PostResult

interface PostRepository {
    suspend fun createPost(authorId: String, content: String, imageUrls: List<String> = emptyList(), hashtags: List<String> = emptyList()): PostResult
}
