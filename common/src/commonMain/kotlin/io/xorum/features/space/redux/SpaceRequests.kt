package io.xorum.features.space.redux

import io.xorum.features.space.entity.PeopleInSpace
import io.xorum.features.space.repository.SpaceRepository
import io.xorum.network.Response
import io.xorum.redux.Request
import io.xorum.redux.ToastAction
import io.xorum.redux.store
import tw.geothings.rekotlin.Action

class SpaceRequests {

    class FetchPeopleInSpace : Request() {

        private val spaceRepository: SpaceRepository = SpaceRepository()

        override suspend fun execute() {
            val result = when (val response = spaceRepository.getPeopleInSpace()) {
                is Response.Success -> Success(response.result)
                is Response.Failure -> Failure(response.error)
            }
            store.dispatch(result)
        }

        data class Success(val peopleInSpace: PeopleInSpace) : Action
        data class Failure(override val message: String?) : ToastAction
    }
}
