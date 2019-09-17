package ru.diitcenter.optovik.di.global.modules

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.diitcenter.optovik.data.global.TokenInterceptor
import ru.diitcenter.optovik.data.prefs.PrefsHelper
import ru.example.optovik.BuildConfig
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.BASIC
            }
        }

    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor, tokenInterceptor: TokenInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(tokenInterceptor)
            .build()

    @Provides
    @Singleton
    @Named("MAIN_RETROFIT")
    fun provideMainRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(ru.diitcenter.optovik.di.global.modules.NetworkModule.Companion.BASE_API_URL_OPTOVIK)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    @Named("BASE_API_URL_OPTOVIK")
    fun provideBaseUrlOptovik() = BASE_API_URL_OPTOVIK



    @Provides
    @Singleton
    fun provideRxJavaAdapter(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }

    @Provides
    @Singleton
    @Named("API_OPTOVIK")
    fun provideOptovikApi(@Named("MAIN_RETROFIT") retrofit: Retrofit) =
        retrofit.create(ru.diitcenter.optovik.data.network.MainApi::class.java)

    companion object {

        private const val BASE_API_URL_OPTOVIK = "http://muradbfr.beget.tech"


    }
}