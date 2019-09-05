package ru.diitcenter.optovik

import android.app.Application
import com.arellomobile.mvp.MvpFacade
import ru.diitcenter.optovik.di.global.DaggerAppComponent

class App : Application(){

override fun onCreate() {
    super.onCreate()
    MvpFacade.init()
    ru.diitcenter.optovik.App.Companion.appComponent = buildAppComponent()
}

private fun buildAppComponent(): ru.diitcenter.optovik.di.global.AppComponent =
    DaggerAppComponent.builder()
        .bindsInstanceContext(this)
        .build()

companion object {
    lateinit var appComponent: ru.diitcenter.optovik.di.global.AppComponent
}
}