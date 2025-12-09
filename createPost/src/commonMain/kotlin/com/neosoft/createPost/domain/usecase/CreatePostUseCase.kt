package com.neosoft.createPost.domain.usecase

import com.neosoft.createPost.domain.entity.PostResult
import com.neosoft.createPost.domain.repository.PostRepository

class CreatePostUseCase(private val repository: PostRepository) {
    suspend operator fun invoke(authorId: String, content: String, imageUrls: List<String>, hashtags: List<String>): PostResult {
        return repository.createPost(authorId, content, imageUrls, hashtags)
    }
}
