package com.masilotti.bridgecomponents.demo

import android.app.Application
import com.masilotti.bridgecomponents.shared.Bridgework
import dev.hotwire.core.bridge.KotlinXJsonConverter
import dev.hotwire.core.config.Hotwire
import dev.hotwire.navigation.config.registerBridgeComponents

class DemoApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Hotwire.config.jsonConverter = KotlinXJsonConverter()
        Hotwire.registerBridgeComponents(*Bridgework.coreComponents)
    }
}
