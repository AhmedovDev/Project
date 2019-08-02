package com.example.optovik.di.global.modules

import com.example.optovik.data.basketholder.BasketHolder
import com.example.optovik.data.global.DataManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class BasketModule {

    @Provides
    @Singleton
    fun provideBasket() : BasketHolder =
        BasketHolder()
}