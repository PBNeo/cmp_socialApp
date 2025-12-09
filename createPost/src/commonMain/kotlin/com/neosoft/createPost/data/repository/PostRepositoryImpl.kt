package com.neosoft.createPost.data.repository

import com.neosoft.createPost.data.remote.CreatePostRequest
import com.neosoft.createPost.data.remote.PostApiService
import com.neosoft.createPost.domain.entity.PostResult
import com.neosoft.createPost.domain.repository.PostRepository

class PostRepositoryImpl(private val api: PostApiService) : PostRepository {
    override suspend fun createPost(authorId: String, content: String, imageUrls: List<String>, hashtags: List<String>): PostResult {
        val req = CreatePostRequest(
            authorId = authorId,
            content = content,
            imageUrls = imageUrls,
            hashtags = hashtags
        )
        val resp = api.createPostMock(req) // swap to api.createPost(req)
        return PostResult(success = resp.success, postId = resp.postId, message = resp.message)
    }
}
