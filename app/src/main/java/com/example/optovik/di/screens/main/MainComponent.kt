package com.example.optovik.di.screens.main

import com.example.optovik.AutorizationActivity
import com.example.optovik.di.screens.autorization.AutorizationModule
import com.example.optovik.di.screens.autorization.AutorizationScope
import com.example.optovik.presentation.screens.main.ui.MainActivity
import dagger.Subcomponent


@MainScope
@Subcomponent(modules = [MainModule::class])
interface MainComponent {

    fun inject(mainActivity: MainActivity)

    @Subcomponent.Builder
    interface Builder {
        fun build(): MainComponent
    }
}
