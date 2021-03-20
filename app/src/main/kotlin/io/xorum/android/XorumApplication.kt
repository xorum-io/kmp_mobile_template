package io.xorum.android

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import io.xorum.android.components.AndroidMessageHandler
import io.xorum.redux.middlewares.messageHandlers

class XorumApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        app = this
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        messageHandlers.add(AndroidMessageHandler())
    }

    companion object {

        lateinit var app: XorumApplication
    }
}

