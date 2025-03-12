package com.masilotti.demo.components // Replace with your package name.

import android.util.Log
import android.view.Gravity
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.masilotti.demo.R
import dev.hotwire.core.bridge.BridgeComponent
import dev.hotwire.core.bridge.BridgeDelegate
import dev.hotwire.core.bridge.Message
import dev.hotwire.navigation.destinations.HotwireDestination
import dev.hotwire.navigation.fragments.HotwireFragment
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// Requires the androidx.compose.material3:material3 dependency.
class MenuComponent(
    name: String,
    private val bridgeDelegate: BridgeDelegate<HotwireDestination>
) : BridgeComponent<HotwireDestination>(name, bridgeDelegate) {
    private val menuViewId = 42

    private val fragment: HotwireFragment
        get() = bridgeDelegate.destination.fragment as HotwireFragment

    override fun onReceive(message: Message) {
        when (message.event) {
            "connect" -> addMenuButton(message)
            "disconnect" -> removeMenuButton()
            else -> Log.w("Menu Component", "Unknown event: ${message.event}")
        }
    }

    private fun addMenuButton(message: Message) {
        val actionBar = fragment.requireActivity().actionBar
        actionBar?.setDisplayShowHomeEnabled(true)
        actionBar?.setHomeButtonEnabled(true)
        actionBar?.setHomeAsUpIndicator(R.drawable.ic_launcher_foreground)

        val data = message.data<MessageData>() ?: return

        val composeView = ComposeView(fragment.requireContext()).apply {
            id = menuViewId
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MenuDropdown(
                    data = data,
                    onItemSelected = { index ->
                        replyTo(message.event, SelectionMessageData(index))
                    }
                )
            }
        }

        val layoutParams = Toolbar.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        ).apply {
            gravity = Gravity.END
        }

        val toolbar = fragment.toolbarForNavigation()
        toolbar?.addView(composeView, layoutParams)
    }

    private fun removeMenuButton() {
        val toolbar = fragment.toolbarForNavigation()
        val existingView = toolbar?.findViewById<ComposeView>(menuViewId)
        toolbar?.removeView(existingView)
    }

    @Serializable
    data class MessageData(
        val items: List<Item>
    )

    @Serializable
    data class Item(
        val title: String,
        @SerialName("androidImage") val imageName: String?
    )

    @Serializable
    data class SelectionMessageData(val index: Int)
}

@Composable
private fun MenuDropdown(
    data: MenuComponent.MessageData, onItemSelected: (Int) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    IconButton(onClick = { expanded = !expanded }) {
        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = "Menu"
        )
    }

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        data.items.forEachIndexed { index, item ->
            DropdownMenuItem(
                trailingIcon = {
                    item.imageName?.let {
                        Text(
                            text = it,
                            fontFamily = FontFamily(Font(R.font.material_symbols)),
                            fontSize = 20.sp,
                            style = TextStyle(
                                fontFeatureSettings = "liga"
                            )
                        )
                    }
                },
                text = { Text(item.title) },
                onClick = {
                    expanded = false
                    onItemSelected(index)
                }
            )
        }
    }
}
