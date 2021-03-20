package io.xorum.redux

import io.xorum.features.space.redux.spaceReducer
import tw.geothings.rekotlin.Action

fun appReducer(action: Action, state: AppState?): AppState {
    requireNotNull(state)
    return AppState(
            space = spaceReducer(action, state)
    )
}
