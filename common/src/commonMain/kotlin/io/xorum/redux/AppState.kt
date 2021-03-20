package io.xorum.redux

import io.xorum.features.space.redux.SpaceState
import tw.geothings.rekotlin.StateType

data class AppState(
        val space: SpaceState = SpaceState()
) : StateType
