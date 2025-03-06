package com.masilotti.demo.activities

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import com.masilotti.demo.R
import dev.hotwire.navigation.activities.HotwireActivity
import dev.hotwire.navigation.navigator.NavigatorConfiguration
import dev.hotwire.navigation.util.applyDefaultImeWindowInsets

const val rootURL = "http://10.0.2.2:3000"

class MainActivity : HotwireActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<View>(R.id.main_nav_host).applyDefaultImeWindowInsets()
    }

    override fun navigatorConfigurations() = listOf(
        NavigatorConfiguration(
            name = "main",
            startLocation = rootURL,
            navigatorHostId = R.id.main_nav_host
        )
    )
}
