package com.example.optovik.presentation.screens.notofication.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.optovik.App
import com.example.optovik.R

class NotificationActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.notificationComponentBuilder()
            .build()
            .inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)
    }
}
