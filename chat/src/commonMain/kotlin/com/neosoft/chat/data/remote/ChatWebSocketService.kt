package com.neosoft.chat.data.remote
import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.*
import io.ktor.websocket.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class WsEnvelope(val type: String, val payload: String)

@Serializable
data class WsChatMessage(
    val id: String,
    val chatId: String,
    val from: String,
    val to: String,
    val content: String,
    val timestamp: Long,
    val messageType: String = "text"
)

class ChatWebSocketService(
    private val client: HttpClient,
    private val wsUrl: String
) {
    private val scope = CoroutineScope(Dispatchers.IO)
    private var session: DefaultClientWebSocketSession? = null
    private val incomingChannel = Channel<String>(Channel.BUFFERED)

    fun incomingFlow(): Flow<String> = incomingChannel.consumeAsFlow()

    suspend fun connect(authToken: String? = null) {
        if (session != null) return
        session = client.webSocketSession {
//            url(wsUrl)
//            authToken?.let { header("Authorization", "Bearer $it") }
//            this.timeout(30.toDuration(DurationUnit.SECONDS))
        }
        scope.launch {
            try {
                val s = session ?: return@launch
                for (frame in s.incoming) {
                    if (frame is Frame.Text) {
                        incomingChannel.send(frame.readText())
                    }
                }
            } catch (t: Throwable) {
                t.printStackTrace()
            }
        }
    }

    suspend fun disconnect() {
        session?.close(CloseReason(CloseReason.Codes.NORMAL, "Client disconnect"))
        session = null
    }

    suspend fun sendChatMessage(message: WsChatMessage) {
        val s = session ?: throw IllegalStateException("Not connected")
        val envelope = WsEnvelope(type = "chat_message", payload = Json.encodeToString(message))
        s.send(Frame.Text(Json.encodeToString(envelope)))
    }

    suspend fun sendEnvelope(envelope: WsEnvelope) {
        val s = session ?: throw IllegalStateException("Not connected")
        s.send(Frame.Text(Json.encodeToString(envelope)))
    }
}
