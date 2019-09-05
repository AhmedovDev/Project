package ru.diitcenter.optovik.di.global.modules

import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface DataModule {
    @Binds
    @Singleton
    fun provideDataManager(dataManager: ru.diitcenter.optovik.data.global.DataManagerlmpl): ru.diitcenter.optovik.data.global.DataManager
}