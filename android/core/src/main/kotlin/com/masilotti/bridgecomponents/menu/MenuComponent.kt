package com.masilotti.bridgecomponents.menu

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
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.masilotti.bridgecomponents.R
import com.masilotti.bridgecomponents.shared.Colors
import dev.hotwire.core.bridge.BridgeComponent
import dev.hotwire.core.bridge.BridgeDelegate
import dev.hotwire.core.bridge.Message
import dev.hotwire.navigation.destinations.HotwireDestination
import dev.hotwire.navigation.fragments.HotwireFragment
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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
            else -> Log.w("MenuComponent", "Unknown event: ${message.event}")
        }
    }

    private fun addMenuButton(message: Message) {
        removeMenuButton()

        val actionBar = fragment.requireActivity().actionBar
        actionBar?.setDisplayShowHomeEnabled(true)
        actionBar?.setHomeButtonEnabled(true)

        val data = message.data<MessageData>() ?: return

        val composeView = ComposeView(fragment.requireContext()).apply {
            id = menuViewId
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MenuDropdown(
                    data = data,
                    contentColor = Colors.bridgeworkColor("menu", hex = data.colorCode),
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
        val items: List<Item>,
        @SerialName("color") val colorCode: String?
    )

    @Serializable
    data class Item(
        val title: String,
        @SerialName("androidImage") val imageName: String?,
        val destructive: Boolean?
    )

    @Serializable
    data class SelectionMessageData(val index: Int)
}

@Composable
private fun MenuDropdown(
    data: MenuComponent.MessageData,
    contentColor: Color,
    onItemSelected: (Int) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    IconButton(
        onClick = { expanded = !expanded },
        colors = IconButtonDefaults.iconButtonColors(
            contentColor = contentColor
        )
    ) {
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
            val color = if (item.destructive == true) {
                MaterialTheme.colorScheme.error
            } else {
                MaterialTheme.colorScheme.onSurface
            }
            DropdownMenuItem(
                trailingIcon = {
                    item.imageName?.let {
                        Text(
                            text = it,
                            fontFamily = FontFamily(Font(R.font.material_symbols)),
                            fontSize = 20.sp,
                            style = TextStyle(
                                fontFeatureSettings = "liga"
                            ),
                            color = color
                        )
                    }
                },
                text = {
                    Text(
                        text = item.title,
                        color = color
                    )
                },
                onClick = {
                    expanded = false
                    onItemSelected(index)
                }
            )
        }
    }
}
