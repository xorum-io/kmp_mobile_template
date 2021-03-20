package io.xorum.network

import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.http.*

const val BACKEND_LINK = "api.open-notify.org"

class HttpClientFactory {

    fun create() = HttpClient {
        defaultRequest {
            url {
                host = BACKEND_LINK
                protocol = URLProtocol.HTTP
            }
        }
        Json {
            serializer = KotlinxSerializer(
                    json = kotlinx.serialization.json.Json(from = kotlinx.serialization.json.Json.Default) {
                        ignoreUnknownKeys = true
                        useArrayPolymorphism = true
                    }
            )
        }
        Logging {
            logger = Logger.DEFAULT
            level = LogLevel.INFO
        }
    }
}
