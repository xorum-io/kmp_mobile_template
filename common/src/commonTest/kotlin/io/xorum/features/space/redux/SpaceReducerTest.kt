package io.xorum.features.space.redux

import io.xorum.features.space.entity.PeopleInSpace
import io.xorum.redux.AppState
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
class SpaceReducerTest {

    @Test
    fun testFetchPeopleInSpaceSuccess() {
        val expected = SpaceState(PeopleInSpace(42), SpaceState.Status.IDLE)
        val actual = spaceReducer(SpaceRequests.FetchPeopleInSpace.Success(PeopleInSpace(42)), AppState())
        assertEquals(expected, actual)
    }
}
