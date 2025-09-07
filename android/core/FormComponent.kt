package com.masilotti.demo.components // Replace with your package name.

import android.util.Log
import android.view.Gravity
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import dev.hotwire.core.bridge.BridgeComponent
import dev.hotwire.core.bridge.BridgeDelegate
import dev.hotwire.core.bridge.Message
import dev.hotwire.navigation.destinations.HotwireDestination
import dev.hotwire.navigation.fragments.HotwireFragment
import kotlinx.serialization.Serializable

class FormComponent(
    name: String,
    private val bridgeDelegate: BridgeDelegate<HotwireDestination>
) : BridgeComponent<HotwireDestination>(name, bridgeDelegate) {
    private val buttonId = 1
    private var isButtonEnabled: MutableState<Boolean>? = null
    private val fragment: HotwireFragment
        get() = bridgeDelegate.destination.fragment as HotwireFragment

    override fun onReceive(message: Message) {
        when (message.event) {
            "connect" -> addButton(message)
            "disconnect" -> removeButton()
            "enableSubmit" -> enableButton()
            "disableSubmit" -> disableButton()
            else -> Log.w("FormComponent", "Unknown event for message: $message")
        }
    }

    private fun addButton(message: Message) {
        val data = message.data<MessageData>() ?: return
        removeButton()

        val composeView = ComposeView(fragment.requireContext()).apply {
            id = buttonId
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                val enabledState = remember { mutableStateOf(true) }
                isButtonEnabled = enabledState

                SubmitButton(
                    title = data.title,
                    enabled = enabledState.value,
                    onClick = { replyTo(message.event) }
                )
            }
        }
        val layoutParams = Toolbar.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        ).apply { gravity = Gravity.END }

        val toolbar = fragment.toolbarForNavigation()
        toolbar?.addView(composeView, layoutParams)
    }

    private fun removeButton() {
        val toolbar = fragment.toolbarForNavigation()
        val button = toolbar?.findViewById<ComposeView>(buttonId)
        toolbar?.removeView(button)
    }

    private fun enableButton() {
        isButtonEnabled?.value = true
    }

    private fun disableButton() {
        isButtonEnabled?.value = false
    }

    @Serializable
    data class MessageData(
        val title: String
    )
}

@Composable
private fun SubmitButton(
    title: String, enabled: Boolean, onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent, contentColor = Color.Black
        )
    ) {
        Text(title)
    }
}
