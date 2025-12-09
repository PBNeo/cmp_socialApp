package com.neosoft.lounge.data.remote
import io.ktor.client.*
import kotlinx.coroutines.delay
import kotlinx.serialization.Serializable
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@Serializable
data class LoungeDto(
    val id: String,
    val title: String,
    val description: String,
    val listenerCount: Int,
    val hostId: String,
    val hostName: String,
    val coverUrl: String? = null
)

@Serializable
data class LoungeDetailsDto(
    val id: String,
    val title: String,
    val description: String,
    val hostId: String,
    val hostName: String,
    val participants: List<ParticipantDto>
)

@Serializable
data class ParticipantDto(
    val userId: String,
    val name: String,
    val avatarUrl: String? = null,
    val role: String = "listener" // listener, speaker, host
)

class LoungeApiService(private val client: HttpClient? = null) {

    // Mock implementations for UI/dev purposes
    suspend fun getLounges(): List<LoungeDto> {
        delay(200)
        return listOf(
            LoungeDto(id = "l1", title = "The Chirp app is live on App Store", description = "Selected based on your friends interest", listenerCount = 200_000, hostId = "u1", hostName = "Oyin Dolapo", coverUrl = null),
            LoungeDto(id = "l2", title = "Happening Now: Tech Talks", description = "Ofoos going on at the moment", listenerCount = 1200, hostId = "u2", hostName = "Abdul Q", coverUrl = null)
        )
    }

    @OptIn(ExperimentalTime::class)
    suspend fun createLounge(title: String, description: String): LoungeDto {
        delay(300)
        return LoungeDto(id = "l_${Clock.System.now().toEpochMilliseconds()}", title = title, description = description, listenerCount = 1, hostId = "u_me", hostName = "You", coverUrl = null)
    }

    suspend fun getLoungeDetails(loungeId: String): LoungeDetailsDto {
        delay(200)
        val participants = (1..16).map {
            ParticipantDto(userId = "p$it", name = "Chris $it", avatarUrl = null, role = if (it==1) "host" else "listener")
        }
        return LoungeDetailsDto(id = loungeId, title = "Lounge $loungeId", description = "Details for $loungeId", hostId = "u1", hostName = "Oyin Dolapo", participants = participants)
    }

    // Multipart upload stub - replace URL + body builder as per your server
//    suspend fun uploadCoverImage(file: File, uploadUrl: String = "http://10.0.2.2:8090/api/upload") : String {
//        if (client == null) throw IllegalStateException("HttpClient not provided")
//        val response: HttpResponse = client.submitFormWithBinaryData(
//            url = uploadUrl,
//            formData = formData {
//                append("file", file, Headers.build {
//                    append(HttpHeaders.ContentType, "image/jpeg")
//                    append(HttpHeaders.ContentDisposition, "filename=\"${file.name}\"")
//                })
//            }
//        )
//        // Parse response and return uploaded URL - mocked here
//        return response.readText() // adapt to your API
//    }
}
