package io.xorum.features.space.repository

import io.ktor.client.*
import io.ktor.client.request.*
import io.xorum.features.space.entity.PeopleInSpace
import io.xorum.network.HttpClientFactory
import io.xorum.network.request

internal class SpaceRepository(
        private val httpClient: HttpClient = HttpClientFactory().create()
) {

    suspend fun getPeopleInSpace() = request {
        httpClient.get<PeopleInSpace>(path = "astros.json")
    }
}
