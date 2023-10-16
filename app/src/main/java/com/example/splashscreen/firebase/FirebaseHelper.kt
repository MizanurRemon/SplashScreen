package com.example.splashscreen.firebase

import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import com.google.firebase.analytics.FirebaseAnalytics

fun trackNavigationEvents(navController: NavController) {
    navController.addOnDestinationChangedListener { _, destination, _ ->
        val screenName = destination.route
        val eventName = "screen_view"
        val params = Bundle()
        params.putString(FirebaseAnalytics.Param.SCREEN_NAME, screenName)
        FirebaseAnalytics.getInstance(navController.context).logEvent(eventName, params)
        Log.d("dataxx", "trackNavigationEvents: $screenName")
    }
}