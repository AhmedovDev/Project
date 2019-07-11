package com.example.optovik.di.screens.autorization

import com.example.optovik.AutorizationActivity
import dagger.Subcomponent


@AutorizationScope
@Subcomponent(modules = [AutorizationModule::class])
interface AutorizationComponent {

    fun inject(autorizationActivity: AutorizationActivity)

    @Subcomponent.Builder
    interface Builder {
        fun build(): AutorizationComponent
    }
}
