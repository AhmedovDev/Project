package com.example.optovik.di.screens.splash

import com.example.optovik.presentation.screens.splash.ui.SplashActivity
import dagger.Subcomponent

@SplashScope
@Subcomponent(modules = [SplashModule::class])
interface SplashComponent {

    fun inject(splashActivity: SplashActivity)

    @Subcomponent.Builder
    interface Builder {
        fun build(): SplashComponent
    }
}
