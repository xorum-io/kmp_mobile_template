package io.xorum.android

import android.app.Application
import io.xorum.android.components.AndroidMessageHandler
import io.xorum.redux.middlewares.messageHandlers
import io.xorum.util.Strings

class XorumApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        app = this

        initMessageHandler()
        initCommonStrings()
    }

    private fun initMessageHandler() {
        messageHandlers.add(AndroidMessageHandler())
    }

    private fun initCommonStrings() {
        Strings.context = this
    }

    companion object {

        lateinit var app: XorumApplication
    }
}
