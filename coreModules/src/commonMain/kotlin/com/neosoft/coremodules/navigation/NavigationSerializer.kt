import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json

object NavigationSerializer {
    private val json = Json { encodeDefaults = true }

    fun <T> encode(obj: T, serializer: KSerializer<T>): String =
        json.encodeToString(serializer, obj)

    fun <T> decode(encoded: String, serializer: KSerializer<T>): T =
        json.decodeFromString(serializer, encoded)
}
