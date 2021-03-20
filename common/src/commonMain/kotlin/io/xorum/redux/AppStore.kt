package io.xorum.redux

import io.xorum.redux.middlewares.messageMiddleware
import io.xorum.redux.middlewares.appMiddleware
import tw.geothings.rekotlin.Store

val store by lazy {
    Store(
            reducer = ::appReducer,
            state = AppState(),
            middleware = listOf(appMiddleware, messageMiddleware)
    )
}
