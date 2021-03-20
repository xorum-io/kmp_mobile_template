package io.xorum.redux

import tw.geothings.rekotlin.Action

interface ToastAction : Action {

    val message: String?
}
