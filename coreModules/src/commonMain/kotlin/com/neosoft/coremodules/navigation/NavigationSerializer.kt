import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import io.ktor.http.decodeURLQueryComponent
import io.ktor.http.encodeURLParameter


object NavigationSerializer {

    private val json = Json { ignoreUnknownKeys = true }

    fun <T> encode(value: T, serializer: KSerializer<T>): String {
        val serialized = json.encodeToString(serializer, value)
        return serialized.encodeURLParameter().encodeURLParameter() // double encode
    }

    fun <T> decode(input: String, serializer: KSerializer<T>): T {
        val decoded = input.decodeURLQueryComponent().decodeURLQueryComponent() // double decode
        return json.decodeFromString(serializer, decoded)
    }
}

