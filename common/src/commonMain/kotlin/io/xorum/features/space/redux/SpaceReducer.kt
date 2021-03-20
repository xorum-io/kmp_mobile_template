package io.xorum.features.space.redux

import io.xorum.redux.AppState
import tw.geothings.rekotlin.Action

fun spaceReducer(action: Action, state: AppState): SpaceState {
    var newState = state.space

    when (action) {
        is SpaceRequests.FetchPeopleInSpace -> {
            newState = newState.copy(status = SpaceState.Status.PENDING)
        }
        is SpaceRequests.FetchPeopleInSpace.Success -> {
            newState = newState.copy(
                    peopleInSpace = action.peopleInSpace,
                    status = SpaceState.Status.IDLE
            )
        }
        is SpaceRequests.FetchPeopleInSpace.Failure -> {
            newState = newState.copy(status = SpaceState.Status.IDLE)
        }
    }

    return newState
}
