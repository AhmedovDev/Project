package com.example.optovik

import android.app.Application
import com.arellomobile.mvp.MvpFacade
import com.example.optovik.di.global.AppComponent
import com.example.optovik.di.global.DaggerAppComponent

class App : Application(){

override fun onCreate() {
    super.onCreate()
    MvpFacade.init()
    appComponent = buildAppComponent()
}

private fun buildAppComponent(): AppComponent =
    DaggerAppComponent.builder()
        .bindsInstanceContext(this)
        .build()

companion object {
    lateinit var appComponent: AppComponent
}
}