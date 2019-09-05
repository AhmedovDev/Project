package ru.diitcenter.optovik.di.global.modules

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class BasketModule {

    @Provides
    @Singleton
    fun provideBasket() : ru.diitcenter.optovik.data.basketholder.BasketHolder =
        ru.diitcenter.optovik.data.basketholder.BasketHolder()
}