package io.xorum.android.features

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.xorum.android.databinding.ActivityMainBinding
import io.xorum.features.space.redux.SpaceRequests
import io.xorum.features.space.redux.SpaceState
import io.xorum.redux.store
import tw.geothings.rekotlin.StoreSubscriber

class MainActivity : AppCompatActivity(), StoreSubscriber<SpaceState> {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        store.dispatch(SpaceRequests.FetchPeopleInSpace())
    }

    override fun onNewState(state: SpaceState) = with(binding) {
        textView.text = when (state.status) {
            SpaceState.Status.IDLE -> state.peopleInSpace?.number.toString()
            SpaceState.Status.PENDING -> "Loading ..."
        }
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
}
