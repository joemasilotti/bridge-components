package com.masilotti.bridgecomponents.button

import android.util.Log
import android.view.Gravity
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.masilotti.bridgecomponents.R
import com.masilotti.bridgecomponents.shared.Colors
import com.masilotti.bridgecomponents.shared.colorOnSurface
import dev.hotwire.core.bridge.BridgeComponent
import dev.hotwire.core.bridge.BridgeDelegate
import dev.hotwire.core.bridge.Message
import dev.hotwire.navigation.destinations.HotwireDestination
import dev.hotwire.navigation.fragments.HotwireFragment
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class ButtonComponent(
    name: String,
    private val bridgeDelegate: BridgeDelegate<HotwireDestination>
) : BridgeComponent<HotwireDestination>(name, bridgeDelegate) {
    private val buttonId = 1
    private val fragment: HotwireFragment
        get() = bridgeDelegate.destination.fragment as HotwireFragment

    override fun onReceive(message: Message) {
        when (message.event) {
            "right" -> addButton(message)
            // TODO: Add "left" button handling like on iOS.
            "disconnect" -> removeButton()
            else -> Log.w("ButtonComponent", "Unknown event for message: $message")
        }
    }

    private fun addButton(message: Message) {
        removeButton()

        val data = message.data<MessageData>() ?: return
        val toolbar = fragment.toolbarForNavigation() ?: return

        val composeView = ComposeView(fragment.requireContext()).apply {
            id = buttonId
            setContent {
                ToolbarButton(
                    title = data.title,
                    imageName = data.imageName,
                    contentColor = Colors.bridgeworkColor("button", hex = data.colorCode),
                    onClick = { replyTo(message.event) })
            }
        }
        val layoutParams = Toolbar.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        ).apply { gravity = Gravity.END }

        toolbar.addView(composeView, layoutParams)
    }

    private fun removeButton() {
        val toolbar = fragment.toolbarForNavigation()
        val button = toolbar?.findViewById<ComposeView>(buttonId)
        toolbar?.removeView(button)
    }

    @Serializable
    data class MessageData(
        val title: String,
        @SerialName("androidImage") val imageName: String?,
        @SerialName("color") val colorCode: String?
    )
}

@Composable
private fun ToolbarButton(
    title: String,
    imageName: String?,
    contentColor: Color,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = contentColor
        )
    ) {
        imageName?.let {
            Text(
                text = it,
                fontFamily = FontFamily(Font(R.font.material_symbols)),
                fontSize = 28.sp,
                style = TextStyle(fontFeatureSettings = "liga")
            )
        } ?: Text(title)
    }
}
