package com.example.optovik.pushnotification

import android.util.Log
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService


class MyFirebaseInstanceService : FirebaseInstanceIdService() {

    override fun onTokenRefresh() {
        // Get updated InstanceID token.
        val refreshedToken = FirebaseInstanceId.getInstance().token
        FirebaseMessaging.getInstance().subscribeToTopic("all")
        Log.d(TAG, "Refreshed token: " + refreshedToken!!)

        /* If you want to send messages to this application instance or manage this apps subscriptions on the server side, send the Instance ID token to your app server.*/

        sendRegistrationToServer(refreshedToken)
    }

    private fun sendRegistrationToServer(refreshedToken: String) {
        Log.d("TOKEN ", refreshedToken)
    }

    companion object {

        private val TAG = "MyFirebaseInstanceServi"
    }
}