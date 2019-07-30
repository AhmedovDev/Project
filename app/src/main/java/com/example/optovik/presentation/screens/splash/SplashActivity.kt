package com.example.optovik.presentation.screens.splash

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.os.Handler
import com.example.optovik.AutorizationActivity


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.optovik.R.layout.activity_splash)
//Временная реализация
        val SPLASH_DISPLAY_LENGTH = 1000

        Handler().postDelayed(Runnable {

            val intent = Intent(this, AutorizationActivity::class.java)
            startActivity(intent)
            finish()

        }, SPLASH_DISPLAY_LENGTH.toLong())


    }
}
