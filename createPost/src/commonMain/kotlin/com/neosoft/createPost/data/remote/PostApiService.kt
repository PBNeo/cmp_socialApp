package com.neosoft.createPost.data.remote

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.delay
import kotlinx.serialization.Serializable
import kotlin.time.Clock

@Serializable
data class CreatePostRequest(
    val authorId: String,
    val content: String,
    val imageUrls: List<String> = emptyList(),
    val hashtags: List<String> = emptyList()
)

@Serializable
data class CreatePostResponse(
    val success: Boolean,
    val postId: String? = null,
    val message: String? = null
)

class PostApiService(private val client: HttpClient) {
    suspend fun createPost(request: CreatePostRequest): CreatePostResponse {
        return client.post("posts") {
            setBody(request)
        }.body()
    }

    // Mock
    suspend fun createPostMock(request: CreatePostRequest): CreatePostResponse {
        delay(400)
        return CreatePostResponse(success = true, postId = "post_", message = "Mock created")
    }
}
