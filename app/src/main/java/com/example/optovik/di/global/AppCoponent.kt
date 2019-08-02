package com.example.optovik.di.global

import android.content.Context
import com.example.optovik.data.basketholder.BasketHolder
import com.example.optovik.di.global.modules.*
import com.example.optovik.di.screens.autorization.AutorizationComponent
import com.example.optovik.di.screens.basket.BasketComponent
import com.example.optovik.di.screens.catalog.CatalogComponent
import com.example.optovik.di.screens.inputcode.InputCodeComponent
import com.example.optovik.di.screens.inputphone.InputPhoneComponent
import com.example.optovik.di.screens.main.MainComponent
import com.example.optovik.di.screens.productcard.ProductCardComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        NetworkModule::class,
        NavigationModule::class,
        PrefsModule::class,
        DataModule::class,
        DatabaseModule::class,
        BasketModule::class

    ]
)
@Singleton
interface AppComponent {
    fun autorizationComponentBuilder(): AutorizationComponent.Builder
    fun inputPhoneComponentBuilder(): InputPhoneComponent.Builder
    fun inputCodeComponentBuilder(): InputCodeComponent.Builder
    fun mainComponentBuilder(): MainComponent.Builder
    fun catalogComponentBuilder(): CatalogComponent.Builder
    fun productCardComponentBuilder(): ProductCardComponent.Builder
    fun basketComponentBuilder(): BasketComponent.Builder

    @Component.Builder
    interface Builder {

        fun build(): AppComponent

        @BindsInstance
        fun bindsInstanceContext(context: Context): Builder
    }
}
