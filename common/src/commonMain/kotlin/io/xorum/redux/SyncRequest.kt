package io.xorum.redux

import tw.geothings.rekotlin.Action

/**
 * Use this class when Request is needed to be implemented on iOS too,
 * because there is no support of suspend functions.
 */
abstract class SyncRequest : Action {

    abstract fun execute()
}
