package ru.diitcenter.optovik.pushnotification

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import android.app.NotificationManager
import android.media.RingtoneManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v4.app.NotificationCompat
import ru.diitcenter.optovik.presentation.screens.main.ui.MainActivity
import ru.diitcenter.optovik.presentation.screens.notofication.ui.NotificationActivity
import ru.diitcenter.optovik.presentation.screens.splash.ui.SplashActivity
import ru.example.optovik.R


class MyFirebaseMessagingService : FirebaseMessagingService() {
    val TAG = "FirebaseMessagingService"

    @SuppressLint("LongLogTag")
    override fun onNewToken(token: String) {
        Log.d(TAG, token)
    }

    @SuppressLint("LongLogTag")
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d(TAG, "Dikirim dari: ${remoteMessage.from}")

        if (remoteMessage.notification != null) {


            val categoryId: String? = remoteMessage.data.get("category")
            val orderId: String? = remoteMessage.data.get("order_id")


            Log.e("REMOTE_DATE", "${remoteMessage.data}")

            val type: String? = remoteMessage.data.get("type")
            val status: String? = remoteMessage.data.get("status")
            //  val orderStatus: String? = remoteMessage.data.get("order_status")
            showNotification(
                remoteMessage.notification?.title,
                remoteMessage.notification?.body,
                categoryId?.toInt(),
                orderId?.toInt(),
                type.toString()
            )
            Log.e("PUSH_CATEGORY  ", "$categoryId")
            Log.e("PUSH_TYPE  " , "$type")
            Log.e("PUSH_STATUS  ", "$status")
            Log.e("PUSH_ORDER", "$orderId")
        }
    }

    private fun showNotification(
        title: String?,
        body: String?,
        categoryId: Int?,
        orderId: Int?,
        type: String
    ) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(MainActivity.IS_PUSH_NAVIGATE_KEY, true)
        intent.putExtra(MainActivity.IS_PUSH_NAVIGATE_TYPE, type)
        if(categoryId != null)
        intent.putExtra(MainActivity.IS_PUSH_NAVIGATE_TARGET_ID, categoryId)
        if(orderId != null)
            intent.putExtra(MainActivity.IS_PUSH_NAVIGATE_TARGET_ID, orderId)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent,
            PendingIntent.FLAG_ONE_SHOT
        )
        val channelId = getString(R.string.default_notification_channel_id)
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(title)
            .setContentText(body)
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .setContentIntent(pendingIntent)


        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (notificationManager != null) {
            // Since android Oreo notification channel is needed.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(
                    channelId,
                    getString(R.string.default_notification_title),
                    NotificationManager.IMPORTANCE_DEFAULT
                )
                notificationManager.createNotificationChannel(channel)
            }

            notificationManager.notify(0, notificationBuilder.build())
        }
    }
}