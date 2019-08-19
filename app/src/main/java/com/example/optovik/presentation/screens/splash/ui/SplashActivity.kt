package com.example.optovik.presentation.screens.splash.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.os.Handler
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.optovik.App
import com.example.optovik.data.basketholder.BasketHolder
import com.example.optovik.presentation.screens.main.ui.MainActivity
import com.example.optovik.presentation.screens.splash.mvp.SplashPresenter
import com.example.optovik.presentation.screens.splash.mvp.SplashView
import javax.inject.Inject


class SplashActivity : MvpAppCompatActivity(), SplashView {

    @Inject
    @InjectPresenter
    lateinit var presenter: SplashPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.splashComponentBuilder()
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
        setContentView(com.example.optovik.R.layout.activity_splash)
    }

    override fun intent() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun showError() {
        Toast.makeText(this, "Проблемы с интернетом", Toast.LENGTH_SHORT).show()
    }
}
