package com.neosoft.auth.data.remote
import com.neosoft.auth.data.remote.dto.RegisterRequest
import com.neosoft.auth.data.remote.dto.RegisterResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class AuthApiService(
    private val client: HttpClient
) {
    suspend fun registerUser(request: RegisterRequest): RegisterResponse {
        println(" aoi service")
        return client.post("collections/users/records") {
            setBody(request)

        }.body()
    }

    suspend fun registerUserMock(request: RegisterRequest): RegisterResponse {
        println("Mock API called with: $request")

        // Simulate network delay
        kotlinx.coroutines.delay(500)

        // Return a fake response
        return RegisterResponse(
            userId = "12345",
            token = "mock_token",
            refreshToken = "mock_refresh_token"
        )
    }

}
