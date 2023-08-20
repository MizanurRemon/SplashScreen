package com.example.splashscreen.firebase

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.splashscreen.MainActivity
import com.example.splashscreen.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


@SuppressLint("MissingFirebaseInstanceTokenRefresh")
class FirebaseMessagingService : FirebaseMessagingService() {
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        Log.d("dataxx", "onMessageReceived: from ${message.from}")

        if (message.data.isNotEmpty()) {
            Log.d("dataxx", "onMessageReceived: data ${message.data}")
        }

        message.data.let {
            Log.d("dataxx", "onMessageReceived:body ${it["body"]}")
            showNotificationOnStatusBar(it)
        }

        if (message.notification != null) {
            Log.d("dataxx", "onMessageReceived: notificationTitle ${message.notification?.title}")
            Log.d("dataxx", "onMessageReceived: notificationBody ${message.notification?.body}")
        }
    }

    @SuppressLint("MissingPermission")
    private fun showNotificationOnStatusBar(data: Map<String, String>) {

        Log.d("dataxx", "showNotificationOnStatusBar: ")
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        }

        intent.putExtra("title", data["title"])
        intent.putExtra("body", data["body"])

        val requestCode = System.currentTimeMillis().toInt()

        val pendingIntent: PendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.getActivity(
                this, requestCode,
                intent, PendingIntent.FLAG_MUTABLE
            )
        } else {
            PendingIntent.getActivity(
                this, requestCode,
                intent, PendingIntent.FLAG_IMMUTABLE
            )
        }

        val builder = NotificationCompat
            .Builder(this, "Global")
            .setAutoCancel(true)
            .setContentTitle(data["title"])
            .setContentText(data["body"])
            .setPriority(Notification.PRIORITY_HIGH)
            .setStyle(NotificationCompat.BigTextStyle().bigText(data["body"]))
            .setContentIntent(pendingIntent)
            .setSmallIcon(R.drawable.baseline_back_hand_24)

        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "Global",
                "Default channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            manager.createNotificationChannel(channel)
        }
        manager.notify(0, builder.build())

    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)

        Log.d("dataxx", "onNewToken: $token")

        //  sendRegistrationToServer(token)

    }
}