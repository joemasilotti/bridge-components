package com.masilotti.bridgecomponents.shared

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.masilotti.bridgecomponents.alert.AlertComponent
import com.masilotti.bridgecomponents.button.ButtonComponent
import com.masilotti.bridgecomponents.form.FormComponent
import com.masilotti.bridgecomponents.haptic.HapticComponent
import com.masilotti.bridgecomponents.menu.MenuComponent
import com.masilotti.bridgecomponents.reviewprompt.ReviewPromptComponent
import com.masilotti.bridgecomponents.search.SearchComponent
import com.masilotti.bridgecomponents.share.ShareComponent
import com.masilotti.bridgecomponents.theme.ThemeComponent
import com.masilotti.bridgecomponents.toast.ToastComponent
import dev.hotwire.core.bridge.BridgeComponentFactory
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.launch

object Bridgework {
    val coreComponents = listOf(
        BridgeComponentFactory("alert", ::AlertComponent),
        BridgeComponentFactory("button", ::ButtonComponent),
        BridgeComponentFactory("form", ::FormComponent),
        BridgeComponentFactory("haptic", ::HapticComponent),
        BridgeComponentFactory("menu", ::MenuComponent),
        BridgeComponentFactory("review-prompt", ::ReviewPromptComponent),
        BridgeComponentFactory("search", ::SearchComponent),
        BridgeComponentFactory("share", ::ShareComponent),
        BridgeComponentFactory("theme", ::ThemeComponent),
        BridgeComponentFactory("toast", ::ToastComponent),
    ).toTypedArray()

    // Observe an event with a strongly typed payload:
    // Bridgework.observe(this, Events.themeDidChange) { uiMode: Int ->
    //     Log.d("THEME", uiMode.toString())
    // }
    inline fun <reified T : Any> observe(
        owner: LifecycleOwner,
        event: BridgeNotification<T>,
        crossinline handler: (T) -> Unit
    ): Job = owner.lifecycleScope.launch {
        owner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            bus
                .filter { (name, _) -> name == event.name }
                .mapNotNull { (_, p) -> p as? T }
                .collect { handler(it) }
        }
    }

    // Post an event with a strongly typed payload:
    // Bridgework.post(Events.didReceiveNotificationToken, deviceToken)
    fun <T : Any> post(event: BridgeNotification<T>, payload: T) {
        bus.tryEmit(event.name to payload)
    }

    data class BridgeNotification<T : Any>(val name: String)
    val bus = MutableSharedFlow<Pair<String, Any>>(extraBufferCapacity = 64)
}
