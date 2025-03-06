package com.masilotti.demo

import android.app.Application
import com.masilotti.demo.components.ButtonComponent
import com.masilotti.demo.components.FormComponent
import dev.hotwire.core.bridge.BridgeComponentFactory
import dev.hotwire.core.bridge.KotlinXJsonConverter
import dev.hotwire.core.config.Hotwire
import dev.hotwire.navigation.config.registerBridgeComponents

class DemoApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Hotwire.registerBridgeComponents(
            BridgeComponentFactory("button", ::ButtonComponent),
            BridgeComponentFactory("form", ::FormComponent),

        )

        Hotwire.config.jsonConverter = KotlinXJsonConverter()
    }
}
