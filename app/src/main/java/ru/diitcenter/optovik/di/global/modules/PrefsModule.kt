package ru.diitcenter.optovik.di.global.modules

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PrefsModule {
    @Provides
    @Singleton
    fun provideSharedPreferences(context: Context): SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)

    @Provides
    @Singleton
    fun providePrefsHelper(sharedPrefs: SharedPreferences) =
        ru.diitcenter.optovik.data.prefs.PrefsHelper(sharedPrefs)
}