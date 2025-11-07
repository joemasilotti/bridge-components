package com.masilotti.bridgecomponents.shared

import android.content.Context
import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.google.android.material.color.MaterialColors

object Colors {
    @Composable
    fun bridgeworkColor(named: String, hex: String? = null): androidx.compose.ui.graphics.Color {
        val context = LocalContext.current
        val color = colorFromHex(hex) ?: named(context, named)
        return androidx.compose.ui.graphics.Color(color)
    }

    @ColorInt
    private fun named(context: Context, name: String): Int {
        val res = context.resources
        val pkg = context.packageName

        val specificId = res.getIdentifier(
            "bridgework_${name.lowercase()}_color", "color", pkg
        )
        if (specificId != 0) return ContextCompat.getColor(context, specificId)

        val defaultId = res.getIdentifier("bridgework_color", "color", pkg)
        if (defaultId != 0) return ContextCompat.getColor(context, defaultId)

        return MaterialColors.getColor(
            context,
            com.google.android.material.R.attr.colorOnSurface,
            Color.BLACK
        )
    }
}

@ColorInt
private fun colorFromHex(hex: String?): Int? {
    if (hex.isNullOrBlank()) return null
    val clean = hex.trim().removePrefix("#")

    return try {
        val value = clean.toLong(16)
        val r = ((value shr 16) and 0xFF).toInt()
        val g = ((value shr 8) and 0xFF).toInt()
        val b = (value and 0xFF).toInt()
        Color.argb(0xFF, r, g, b)
    } catch (_: NumberFormatException) {
        null
    }
}
