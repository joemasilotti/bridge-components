package com.masilotti.bridgecomponents.theme

import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
import com.masilotti.bridgecomponents.shared.Bridgework
import com.masilotti.bridgecomponents.shared.Events
import dev.hotwire.core.bridge.BridgeComponent
import dev.hotwire.core.bridge.BridgeDelegate
import dev.hotwire.core.bridge.Message
import dev.hotwire.navigation.destinations.HotwireDestination
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class ThemeComponent(
    name: String,
    bridgeDelegate: BridgeDelegate<HotwireDestination>
) : BridgeComponent<HotwireDestination>(name, bridgeDelegate) {

    override fun onReceive(message: Message) {
        val data = message.data<MessageData>() ?: return
        when (data.theme) {
            Theme.LIGHT -> apply(AppCompatDelegate.MODE_NIGHT_NO)
            Theme.DARK -> apply(AppCompatDelegate.MODE_NIGHT_YES)
            null -> apply(MODE_NIGHT_FOLLOW_SYSTEM)
        }
    }

    private fun apply(@AppCompatDelegate.NightMode theme: Int) {
        if (AppCompatDelegate.getDefaultNightMode() != theme) {
            AppCompatDelegate.setDefaultNightMode(theme)
            Bridgework.post(Events.themeDidChange, theme)
        }
    }

    @Serializable
    private data class MessageData(
        val theme: Theme?
    )

    @Serializable
    private enum class Theme {
        @SerialName("light") LIGHT,
        @SerialName("dark") DARK
    }
}
