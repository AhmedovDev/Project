package com.example.optovik.presentation.screens.splash.mvp

import com.arellomobile.mvp.MvpView
import com.example.optovik.data.global.models.ProductCard

interface SplashView : MvpView {
    fun showError()
    fun intent()
}

