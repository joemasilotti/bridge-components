package com.masilotti.bridgecomponents.shared

import androidx.appcompat.app.AppCompatDelegate

// Posted when ThemeComponent detects a theme change.
// Value is @AppCompatDelegate.NightMode Int.
object Events {
    val themeDidChange = Bridgework.BridgeNotification<Int>("theme-did-change")
}
