package com.masilotti.demo.components // Replace with your package name.

import android.util.Log
import androidx.fragment.app.Fragment
import com.google.android.play.core.review.ReviewManager
import com.google.android.play.core.review.ReviewManagerFactory
import dev.hotwire.core.bridge.BridgeComponent
import dev.hotwire.core.bridge.BridgeDelegate
import dev.hotwire.core.bridge.Message
import dev.hotwire.navigation.destinations.HotwireDestination

class ReviewPromptComponent(
    name: String,
    private val bridgeDelegate: BridgeDelegate<HotwireDestination>
) : BridgeComponent<HotwireDestination>(name, bridgeDelegate) {
    private val fragment: Fragment
        get() = bridgeDelegate.destination.fragment

    private val manager: ReviewManager? by lazy {
        fragment.context?.let {
            ReviewManagerFactory.create(it)
        }
    }

    override fun onReceive(message: Message) {
        when (message.event) {
            "prompt" -> promptForReview()
            else -> Log.w("Review Prompt Component", "Unknown event for message: $message")
        }
    }

    private fun promptForReview() {
        val request = manager?.requestReviewFlow()
        request?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val reviewInfo = task.result
                val flow = fragment.activity?.let { manager?.launchReviewFlow(it, reviewInfo) }
                flow?.addOnCompleteListener { _ ->
                    Log.d("Review Prompt Component", "Fake review flow completed.")
                }
            } else {
                Log.e("Review Prompt Component", task.exception?.message ?: "(no message)")
            }
        }
    }
}
