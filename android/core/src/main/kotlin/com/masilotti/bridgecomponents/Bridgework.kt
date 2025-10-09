package com.masilotti.bridgecomponents

import androidx.annotation.ColorInt
import androidx.appcompat.widget.Toolbar
import androidx.compose.ui.graphics.Color
import com.google.android.material.color.MaterialColors
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
}

@ColorInt
fun colorOnSurface(view: Toolbar): Color {
    val white = 0xFFFFFFFF.toInt()
    return Color(MaterialColors.getColor(view, R.attr.colorOnSurface, white))
}
