package io.xorum.network

import io.ktor.client.features.*
import io.ktor.client.statement.*
import io.ktor.utils.io.charsets.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

internal sealed class Response<T> {

    data class Success<T>(val result: T) : Response<T>()

    data class Failure<T>(val error: String?) : Response<T>()
}

internal suspend inline fun <T> request(block: () -> T) = try {
    Response.Success(block())
} catch (clientRequestException: ClientRequestException) {
    println(clientRequestException)
    val error = getError(clientRequestException.response.readText(Charsets.UTF_8)).error
    Response.Failure(error)
} catch (t: Throwable) {
    println(t)
    Response.Failure(null)
}

internal fun getError(jsonString: String) =
    Json { ignoreUnknownKeys = true }.decodeFromString(NetworkError.serializer(), jsonString)

@Serializable
internal data class NetworkError(val error: String?)

