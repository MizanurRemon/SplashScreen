package com.example.splashscreen.firebase

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.Log
import androidx.annotation.Nullable
import androidx.core.app.NotificationCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.splashscreen.MainActivity
import com.example.splashscreen.R
import com.example.splashscreen.Utils.Constants
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
            Log.d("dataxx", "onMessageReceived:body ${it[Constants.BODY]}")
            showNotificationOnStatusBar(it)
        }

        if (message.notification != null) {
            Log.d("dataxx", "onMessageReceived: notificationTitle ${message.notification?.title}")
            Log.d("dataxx", "onMessageReceived: notificationBody ${message.notification?.body}")
        }
    }

    @SuppressLint("MissingPermission")
    private fun showNotificationOnStatusBar(data: Map<String, String>) {

        Log.d("dataxx", "showNotificationOnStatusBar: $data")
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        }

        intent.putExtra(Constants.TITLE, data[Constants.TITLE])
        intent.putExtra(Constants.BODY, data[Constants.BODY])

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
            .Builder(this, Constants.CHANNEL_ID)
            .setAutoCancel(true)
            .setContentTitle(data[Constants.TITLE])
            .setContentText(data[Constants.BODY])
            .setPriority(Notification.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setSmallIcon(R.drawable.baseline_back_hand_24)

        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                Constants.CHANNEL_ID,
                "Default channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            manager.createNotificationChannel(channel)
        }

        Glide.with(applicationContext)
            .asBitmap()
            .load(data[Constants.ICON])
            .into(object : CustomTarget<Bitmap?>() {
                override fun onLoadCleared(placeholder: Drawable?) {}
                override fun onResourceReady(
                    resource: Bitmap,
                    transition: Transition<in Bitmap?>?
                ) {
                    builder.setLargeIcon(resource)
                    builder.setStyle(
                        NotificationCompat.BigPictureStyle().bigPicture(resource)
                    )
                    val notification: Notification = builder.build()
                    manager.notify(0, notification)
                }
            })
        manager.notify(0, builder.build())

    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)

        //     Log.d("dataxx", "onNewToken: $token")

    }
}