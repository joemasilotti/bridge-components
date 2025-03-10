package com.masilotti.demo.components // Replace with your package name.

import android.util.Log
import androidx.fragment.app.Fragment
import com.google.android.play.core.review.ReviewManager
import com.google.android.play.core.review.ReviewManagerFactory
import com.google.android.play.core.review.testing.FakeReviewManager
import dev.hotwire.core.BuildConfig
import dev.hotwire.core.bridge.BridgeComponent
import dev.hotwire.core.bridge.BridgeDelegate
import dev.hotwire.core.bridge.Message
import dev.hotwire.navigation.destinations.HotwireDestination

// Note that the UI for the review prompt will only appear when running an
// app downloaded from Google Play. Otherwise, FakeReviewManager will
// simulator the API calls.
class ReviewPromptComponent(
    name: String,
    private val bridgeDelegate: BridgeDelegate<HotwireDestination>
) : BridgeComponent<HotwireDestination>(name, bridgeDelegate) {
    private val fragment: Fragment
        get() = bridgeDelegate.destination.fragment

    private val manager: ReviewManager? by lazy {
        fragment.context?.let {
            if (BuildConfig.DEBUG) {
                FakeReviewManager(it) // Use FakeReviewManager in debug mode
            } else {
                ReviewManagerFactory.create(it) // Use real ReviewManager in release mode
            }
        }
    }

    override fun onReceive(message: Message) {
        val request = manager?.requestReviewFlow()
        request?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val reviewInfo = task.result
                val flow = fragment.activity?.let { manager?.launchReviewFlow(it, reviewInfo) }
                flow?.addOnCompleteListener { _ ->
                    Log.d("Review Prompt", "Fake review flow completed.")
                }
            } else {
                Log.e("Review Prompt", task.exception?.message ?: "(no message)")
            }
        }
    }
}
