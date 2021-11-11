package io.xorum.android.features

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.MutableLiveData
import io.xorum.features.space.redux.SpaceRequests
import io.xorum.features.space.redux.SpaceState
import io.xorum.redux.store
import tw.geothings.rekotlin.StoreSubscriber

class MainActivity : ComponentActivity(), StoreSubscriber<SpaceState> {

    private val spaceState = MutableLiveData<SpaceState>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainView()
        }

        store.dispatch(SpaceRequests.FetchPeopleInSpace())
    }

    override fun onNewState(state: SpaceState) {
        spaceState.postValue(state)
    }

    override fun onStart() {
        super.onStart()

        store.subscribe(this) { state ->
            state.skipRepeats { oldState, newState ->
                oldState.space == newState.space
            }.select { it.space }
        }
    }

    override fun onStop() {
        super.onStop()
        store.unsubscribe(this)
    }

    @Composable
    private fun MainView(
        modifier: Modifier = Modifier
    ) {
        val state = spaceState.observeAsState().value ?: return

        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = when (state.status) {
                    SpaceState.Status.IDLE -> state.peopleInSpace?.number.toString()
                    SpaceState.Status.PENDING -> "Loading ..."
                }
            )
        }
    }
}
