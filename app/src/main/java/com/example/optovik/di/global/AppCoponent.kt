package com.example.optovik.di.global

import android.content.Context
import com.example.optovik.di.global.modules.*
import com.example.optovik.di.screens.autorization.AutorizationComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        NetworkModule::class,
        NavigationModule::class,
        PrefsModule::class,
        DataModule::class,
        DatabaseModule::class
    ]
)
@Singleton
interface AppComponent {
   fun autorizationComponentBuilder(): AutorizationComponent.Builder


    @Component.Builder
    interface Builder {

        fun build(): AppComponent

        @BindsInstance
        fun bindsInstanceContext(context: Context): Builder
    }
}
