package com.masilotti.bridgecomponents.shared

import androidx.annotation.ColorInt
import androidx.appcompat.widget.Toolbar
import androidx.compose.ui.graphics.Color
import com.google.android.material.color.MaterialColors

@ColorInt
fun colorOnSurface(view: Toolbar): Color {
    val white = 0xFFFFFFFF.toInt()
    return Color(MaterialColors.getColor(
        view,
        com.google.android.material.R.attr.colorOnSurface,
        white
    ))
}
