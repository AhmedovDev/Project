package com.example.optovik.presentation.splash

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import com.example.optovik.AutorizationActivity


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.optovik.R.layout.activity_splash)

        val intent = Intent(this, AutorizationActivity::class.java)
        startActivity(intent)
        finish()
    }
}
