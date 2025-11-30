package com.neosoft.coremodules.network
import kotlinx.serialization.json.Json
import com.neosoft.coremodules.network.config.NetworkConfig
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.http.contentType


object HttpClientProvider {

    fun create(config: NetworkConfig): HttpClient {
        return HttpClient {

            // 🌐 Base URL + JSON Content-Type
            defaultRequest {
                url(config.baseUrl)
                contentType(io.ktor.http.ContentType.Application.Json)
                accept(io.ktor.http.ContentType.Application.Json)
            }

            // 🔥 JSON Serialization
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        prettyPrint = true
                        isLenient = true
                        encodeDefaults = true
                    }
                )
            }

            // ⌛ Timeouts
            install(HttpTimeout) {
                connectTimeoutMillis = config.connectTimeoutMillis
                requestTimeoutMillis = config.requestTimeoutMillis
                socketTimeoutMillis = config.socketTimeoutMillis
            }

            // 🔐 Authorization Header
            config.tokenProvider?.let { provider ->
                install(DefaultRequest) {
                    provider()?.let { token ->
                        header("Authorization", "Bearer $token")
                    }
                }
            }

            // 📜 Logging
            if (config.enableLogging) {
                install(Logging) {
                    level = LogLevel.ALL
                    logger = object : Logger {
                        override fun log(message: String) {
                            println("Ktor → $message")
                        }
                    }
                }
            }

            // 👀 Response observer
            install(ResponseObserver) {
                onResponse { response ->
                    println("HTTP Status → ${response.status.value}")
                }
            }
        }
    }
}

