package com.example.optovik.pushnotification

import com.squareup.picasso.Picasso
import android.os.Looper
import com.google.firebase.messaging.RemoteMessage
import android.R
import android.app.NotificationManager
import android.app.NotificationChannel
import android.annotation.SuppressLint
import android.app.Notification
import android.os.Build
import android.content.Context.NOTIFICATION_SERVICE
import android.support.v4.content.ContextCompat.getSystemService
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.example.optovik.presentation.screens.main.ui.MainActivity
import android.media.RingtoneManager
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Handler
import android.support.v4.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService


class MyFirebaseMessagingService : FirebaseMessagingService() {

    internal var target: Target = object : Target() {
        fun onBitmapLoaded(bitmap: Bitmap, from: Picasso.LoadedFrom) {
            sendNotification(bitmap)
        }

        fun onBitmapFailed(e: Exception, errorDrawable: Drawable) {

        }

        fun onPrepareLoad(placeHolderDrawable: Drawable) {

        }
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        super.onMessageReceived(remoteMessage)

        if (remoteMessage!!.data != null)
            getImage(remoteMessage)
    }

    private fun sendNotification(bitmap: Bitmap) {


        val style = NotificationCompat.BigPictureStyle()
        style.bigPicture(bitmap)

        val defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val intent = Intent(applicationContext, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        val pendingIntent = PendingIntent.getActivity(applicationContext, 0, intent, 0)

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val NOTIFICATION_CHANNEL_ID = "101"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            @SuppressLint("WrongConstant") val notificationChannel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                "Notification",
                NotificationManager.IMPORTANCE_MAX
            )

            //Configure Notification Channel
            notificationChannel.description = "Game Notifications"
            notificationChannel.enableLights(true)
            notificationChannel.vibrationPattern = longArrayOf(0, 1000, 500, 1000)
            notificationChannel.enableVibration(true)

            notificationManager.createNotificationChannel(notificationChannel)
        }



    companion object {

        private val TAG = "MyFirebaseMessagingServ"
    }
}