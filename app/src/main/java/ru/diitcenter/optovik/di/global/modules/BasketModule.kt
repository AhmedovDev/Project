package ru.diitcenter.optovik.di.global.modules

import dagger.Module
import dagger.Provides
import ru.diitcenter.optovik.data.global.DataManager
import javax.inject.Singleton

@Module
class BasketModule {

    @Provides
    @Singleton
    fun provideBasket(dataManager: DataManager) : ru.diitcenter.optovik.data.basketholder.BasketHolder =
        ru.diitcenter.optovik.data.basketholder.BasketHolder(dataManager)
}