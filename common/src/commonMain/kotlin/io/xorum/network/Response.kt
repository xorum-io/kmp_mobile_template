package io.xorum.network

import io.ktor.client.features.*
import io.ktor.utils.io.*
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
    Response.Failure(getError(clientRequestException.response.content)?.error)
} catch (t: Throwable) {
    println(t)
    Response.Failure(null)
}

internal suspend fun getError(responseContent: ByteReadChannel) = responseContent.readUTF8Line()?.let {
    Json { ignoreUnknownKeys = true }.decodeFromString(NetworkError.serializer(), it)
}

@Serializable
internal data class NetworkError(val error: String?)
