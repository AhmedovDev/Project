package com.example.optovik.di.global.modules

import com.example.optovik.data.global.DataManager
import com.example.optovik.data.global.DataManagerlmpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface DataModule {
    @Binds
    @Singleton
    fun provideDataManager(dataManager: DataManagerlmpl): DataManager
}