package io.xorum.android.components

import android.widget.Toast
import io.xorum.android.XorumApplication
import io.xorum.redux.middlewares.MessageHandler

class AndroidMessageHandler : MessageHandler {

    override fun handle(message: String?) {
        if (message != null) Toast.makeText(XorumApplication.app, message, Toast.LENGTH_LONG).show()
    }
}
