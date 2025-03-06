package com.masilotti.demo.components

import android.view.Gravity
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
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
        if (message.event == "connect") {
            addButton(message)
        } else if (message.event == "disconnect") {
            removeButton()
        }
    }

    private fun addButton(message: Message) {
        val data = message.data<MessageData>() ?: return

        val composeView = ComposeView(fragment.requireContext()).apply {
            id = buttonId
            setContent {
                ToolbarButton(
                    title = data.title,
//                    icon = data.icon,
                    icon = null,
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
}

@Composable
private fun ToolbarButton(
    title: String,
    icon: ImageVector?,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = Color.Black
        )
    ) {
        icon?.let {
            Icon(imageVector = it, contentDescription = title)
        } ?: Text(title)
    }
}

@Composable
@Preview
fun Preview() {
    ToolbarButton(
        title = "Click me",
        icon = null,
        onClick = {}
    )
}

@Serializable
data class MessageData(
    @SerialName("title") val title: String,
//    @SerialName("iconName") val iconName: String?
) {
//    val icon: ImageVector?
//        get() = when (iconName) {
//            "Add" -> Icons.Filled.Add
//            "Edit" -> Icons.Filled.Edit
//            else -> null
//        }
}
