package com.example.optovik.di.global.modules

import com.example.optovik.data.global.models.Basket
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class BasketModule {

    @Provides
    @Singleton
    fun provideBasket() : Basket = Basket()
}