package com.masilotti.demo

import android.app.Application
import com.masilotti.demo.components.AlertComponent
import com.masilotti.demo.components.ButtonComponent
import com.masilotti.demo.components.FormComponent
import com.masilotti.demo.components.HapticComponent
import com.masilotti.demo.components.MenuComponent
import com.masilotti.demo.components.ReviewPromptComponent
import com.masilotti.demo.components.ShareComponent
import com.masilotti.demo.components.ToastComponent
import dev.hotwire.core.bridge.BridgeComponentFactory
import dev.hotwire.core.bridge.KotlinXJsonConverter
import dev.hotwire.core.config.Hotwire
import dev.hotwire.navigation.config.registerBridgeComponents

class DemoApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Hotwire.registerBridgeComponents(
            BridgeComponentFactory("alert", ::AlertComponent),
            BridgeComponentFactory("button", ::ButtonComponent),
            BridgeComponentFactory("form", ::FormComponent),
            BridgeComponentFactory("haptic", ::HapticComponent),
            BridgeComponentFactory("menu", ::MenuComponent),
            BridgeComponentFactory("review-prompt", ::ReviewPromptComponent),
            BridgeComponentFactory("share", ::ShareComponent),
            BridgeComponentFactory("toast", ::ToastComponent)
        )

        Hotwire.config.jsonConverter = KotlinXJsonConverter()
    }
}
