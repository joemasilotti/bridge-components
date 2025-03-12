package com.masilotti.demo.components // Replace with your package name.

import android.util.Log
import androidx.appcompat.app.AlertDialog
import dev.hotwire.core.bridge.BridgeComponent
import dev.hotwire.core.bridge.BridgeDelegate
import dev.hotwire.core.bridge.Message
import dev.hotwire.navigation.destinations.HotwireDestination
import dev.hotwire.navigation.fragments.HotwireFragment
import kotlinx.serialization.Serializable

class AlertComponent(
    name: String,
    private val bridgeDelegate: BridgeDelegate<HotwireDestination>
) : BridgeComponent<HotwireDestination>(name, bridgeDelegate) {
    private val fragment: HotwireFragment
        get() = bridgeDelegate.destination.fragment as HotwireFragment

    override fun onReceive(message: Message) {
        when (message.event) {
            "show" -> showAlert(message)
            else -> Log.w("Alert Component", "Unknown event for message: $message")
        }
    }

    private fun showAlert(message: Message) {
        val data = message.data<MessageData>() ?: return

        AlertDialog.Builder(fragment.requireContext()).setTitle(data.title)
            .setMessage(data.description).setCancelable(true)
            .setNegativeButton(data.dismiss, null)
            .setPositiveButton(data.confirm) { _, _ ->
                replyTo(message.event)
            }.show()
    }

    @Serializable
    private data class MessageData(
        val title: String,
        val description: String?,
        val destructive: Boolean,
        val confirm: String,
        val dismiss: String
    )
}
