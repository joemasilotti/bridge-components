package com.masilotti.bridgecomponents.search

import android.util.Log
import android.view.Gravity
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import dev.hotwire.core.bridge.BridgeComponent
import dev.hotwire.core.bridge.BridgeDelegate
import dev.hotwire.core.bridge.Message
import dev.hotwire.navigation.destinations.HotwireDestination
import dev.hotwire.navigation.fragments.HotwireFragment
import kotlinx.serialization.Serializable

class SearchComponent(
    name: String,
    private val bridgeDelegate: BridgeDelegate<HotwireDestination>
) : BridgeComponent<HotwireDestination>(name, bridgeDelegate) {
    private val searchId = 9918
    private val fragment: HotwireFragment
        get() = bridgeDelegate.destination.fragment as HotwireFragment

    override fun onReceive(message: Message) {
        when (message.event) {
            "connect" -> addSearchView()
            else -> Log.w("SearchComponent", "Unknown event for message: $message")
        }
    }

    private fun addSearchView() {
        if (fragment.toolbarForNavigation()?.findViewById<SearchView>(searchId) != null) return

        val toolbar = fragment.toolbarForNavigation()
        val searchView = SearchView(fragment.requireContext()).apply {
            id = searchId

            queryHint = "Search"
            isFocusable = true
            isFocusableInTouchMode = true

            setOnSearchClickListener {
                layoutParams = Toolbar.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            }

            setOnCloseListener {
                layoutParams = Toolbar.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                ).apply { gravity = Gravity.END }
                false
            }

            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    replyTo("connect", QueryMessageData(query))
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    replyTo("connect", QueryMessageData(newText))
                    return true
                }
            })
        }
        val layoutParams = Toolbar.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        ).apply { gravity = Gravity.END }

        toolbar?.addView(searchView, layoutParams)
    }

    @Serializable
    private data class QueryMessageData(val query: String?)
}
