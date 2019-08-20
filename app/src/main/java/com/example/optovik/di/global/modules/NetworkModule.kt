package com.example.optovik.di.global.modules

import com.example.optovik.BuildConfig
import com.example.optovik.data.network.*
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
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
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

    @Provides
    @Singleton
    @Named("MAIN_RETROFIT")
    fun provideMainRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_API_URL)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    @Named("CATALOG_RETROFIT")
    fun provideCatalogRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_API_URL_CATALOG)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    @Provides
    @Singleton
    @Named("PRODUCT_CARD_RETROFIT")
    fun provideProductCardRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_API_URL_PRODUCTCARD)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    @Provides
    @Singleton
    @Named("BASKET_RETROFIT")
    fun provideBASKETRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_API_URL_PRODUCTCARD)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    @Named("LOCATION_RETROFIT")
    fun provideLocationRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_API_URL_LOCATION)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()




    @Provides
    @Singleton
    @Named("BASE_API_URL")
    fun provideBaseUrlCrm() = BASE_API_URL

    @Provides
    @Singleton
    @Named("BASE_API_URL_CATALOG")
    fun provideBaseUrlPeretz() = BASE_API_URL_CATALOG

    @Provides
    @Singleton
    @Named("BASE_API_URL_PRODUCT_CARD")
    fun provideBaseUrlProductCard() = BASE_API_URL_PRODUCTCARD

    @Provides
    @Singleton
    @Named("BASE_API_URL_BASKET")
    fun provideBaseUrlBasket() = BASE_API_URL_BASKET

    @Provides
    @Singleton
    @Named("BASE_API_URL_LOCATION")
    fun provideBaseUrlLocation() = BASE_API_URL_LOCATION




    @Provides
    @Singleton
    fun provideRxJavaAdapter(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }

    @Provides
    @Singleton
    @Named("API_OPTOVIK")
    fun provideOptovikApi(@Named("MAIN_RETROFIT") retrofit: Retrofit) =
        retrofit.create(OptovikApi::class.java)

    @Provides
    @Singleton
    @Named("API_CATALOG")
    fun provideApiCatalog(@Named("CATALOG_RETROFIT") retrofit: Retrofit) =
        retrofit.create(CatalogApi::class.java)

    @Provides
    @Singleton
    @Named("API_PRODUCT_CARD")
    fun provideApiProductCard(@Named("PRODUCT_CARD_RETROFIT") retrofit: Retrofit) =
        retrofit.create(ProductCardApi::class.java)


    @Provides
    @Singleton
    @Named("API_BASKET")
    fun provideApiBasket(@Named("BASKET_RETROFIT") retrofit: Retrofit) =
        retrofit.create(BasketApi::class.java)

    @Provides
    @Singleton
    @Named("API_LOCATION")
    fun provideApiLocation(@Named("LOCATION_RETROFIT") retrofit: Retrofit) =
        retrofit.create(LocationApi::class.java)

    companion object {
        private const val BASE_API_URL = "https://my-json-server.typicode.com"
        private const val BASE_API_URL_CATALOG = "https://raw.githubusercontent.com"
        private const val BASE_API_URL_PRODUCTCARD = "https://raw.githubusercontent.com"
        private const val BASE_API_URL_BASKET = "https://raw.githubusercontent.com"
        private const val BASE_API_URL_LOCATION = "https://raw.githubusercontent.com"


    }
}