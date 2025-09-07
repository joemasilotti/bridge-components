package com.masilotti.demo.components // Replace with your package name.

import android.os.Build
import android.util.Log
import android.view.HapticFeedbackConstants
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import dev.hotwire.core.bridge.BridgeComponent
import dev.hotwire.core.bridge.BridgeDelegate
import dev.hotwire.core.bridge.Message
import dev.hotwire.navigation.destinations.HotwireDestination
import kotlinx.serialization.Serializable

class HapticComponent(
    name: String,
    private val bridgeDelegate: BridgeDelegate<HotwireDestination>
) : BridgeComponent<HotwireDestination>(name, bridgeDelegate) {
    private val fragment: Fragment
        get() = bridgeDelegate.destination.fragment

    override fun onReceive(message: Message) {
        when (message.event) {
            "vibrate" -> handleVibrateEvent(message)
            else -> Log.w("HapticComponent", "Unknown event for message: $message")
        }
    }

    private fun handleVibrateEvent(message: Message) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            vibrate(message)
        } else {
            Log.e("HapticComponent", "Access to the haptics engine requires Android 11 or later.")
        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    private fun vibrate(message: Message) {
        val data = message.data<MessageData>() ?: return

        when (data.feedback) {
            "success" -> fragment.view?.performHapticFeedback(HapticFeedbackConstants.CONFIRM)
            "warning" -> fragment.view?.performHapticFeedback(HapticFeedbackConstants.REJECT)
            "error" -> fragment.view?.performHapticFeedback(HapticFeedbackConstants.REJECT)
            else -> fragment.view?.performHapticFeedback(HapticFeedbackConstants.CONFIRM)
        }
    }

    @Serializable
    data class MessageData(
        val feedback: String
    )
}