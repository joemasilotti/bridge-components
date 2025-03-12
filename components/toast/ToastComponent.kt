package com.masilotti.demo.components // Replace with your package name.

import android.util.Log
import android.widget.Toast
import dev.hotwire.core.bridge.BridgeComponent
import dev.hotwire.core.bridge.BridgeDelegate
import dev.hotwire.core.bridge.Message
import dev.hotwire.navigation.destinations.HotwireDestination
import kotlinx.serialization.Serializable

class ToastComponent(
    name: String,
    private val bridgeDelegate: BridgeDelegate<HotwireDestination>
) : BridgeComponent<HotwireDestination>(name, bridgeDelegate) {
    override fun onReceive(message: Message) {
        when (message.event) {
            "show" -> showToast(message)
            else -> Log.w("Toast Component", "Unknown event for message: $message")
        }
    }

    private fun showToast(message: Message) {
        val data = message.data<MessageData>() ?: return

        val context = bridgeDelegate.destination.fragment.requireContext()
        Toast.makeText(context, data.message, Toast.LENGTH_SHORT).show()
    }

    @Serializable
    data class MessageData(
        val message: String
    )
}
