package ru.diitcenter.optovik.di.global.modules

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

    @Provides
    @Singleton
    @Named("MAIN_RETROFIT")
    fun provideMainRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(ru.diitcenter.optovik.di.global.modules.NetworkModule.Companion.BASE_API_URL)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    @Named("CATALOG_RETROFIT")
    fun provideCatalogRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(ru.diitcenter.optovik.di.global.modules.NetworkModule.Companion.BASE_API_URL_CATALOG)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    @Provides
    @Singleton
    @Named("PRODUCT_CARD_RETROFIT")
    fun provideProductCardRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(ru.diitcenter.optovik.di.global.modules.NetworkModule.Companion.BASE_API_URL_PRODUCTCARD)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    @Provides
    @Singleton
    @Named("BASKET_RETROFIT")
    fun provideBASKETRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(ru.diitcenter.optovik.di.global.modules.NetworkModule.Companion.BASE_API_URL_PRODUCTCARD)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    @Named("LOCATION_RETROFIT")
    fun provideLocationRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(ru.diitcenter.optovik.di.global.modules.NetworkModule.Companion.BASE_API_URL_LOCATION)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    @Named("MY_ORDER_RETROFIT")
    fun provideMyOrderRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(ru.diitcenter.optovik.di.global.modules.NetworkModule.Companion.BASE_API_URL_MY_ORDER)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    @Named("NOTIFICATION_RETROFIT")
    fun provideNotificationRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(ru.diitcenter.optovik.di.global.modules.NetworkModule.Companion.BASE_API_URL_MY_ORDER)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    @Provides
    @Singleton
    @Named("BASE_API_URL")
    fun provideBaseUrlCrm() =
        ru.diitcenter.optovik.di.global.modules.NetworkModule.Companion.BASE_API_URL

    @Provides
    @Singleton
    @Named("BASE_API_URL_CATALOG")
    fun provideBaseUrlPeretz() =
        ru.diitcenter.optovik.di.global.modules.NetworkModule.Companion.BASE_API_URL_CATALOG

    @Provides
    @Singleton
    @Named("BASE_API_URL_PRODUCT_CARD")
    fun provideBaseUrlProductCard() =
        ru.diitcenter.optovik.di.global.modules.NetworkModule.Companion.BASE_API_URL_PRODUCTCARD

    @Provides
    @Singleton
    @Named("BASE_API_URL_BASKET")
    fun provideBaseUrlBasket() =
        ru.diitcenter.optovik.di.global.modules.NetworkModule.Companion.BASE_API_URL_BASKET

    @Provides
    @Singleton
    @Named("BASE_API_URL_LOCATION")
    fun provideBaseUrlLocation() =
        ru.diitcenter.optovik.di.global.modules.NetworkModule.Companion.BASE_API_URL_LOCATION

    @Provides
    @Singleton
    @Named("BASE_API_URL_MY_ORDER")
    fun provideBaseUrlMyOrder() =
        ru.diitcenter.optovik.di.global.modules.NetworkModule.Companion.BASE_API_URL_MY_ORDER


    @Provides
    @Singleton
    fun provideRxJavaAdapter(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }

    @Provides
    @Singleton
    @Named("API_OPTOVIK")
    fun provideOptovikApi(@Named("MAIN_RETROFIT") retrofit: Retrofit) =
        retrofit.create(ru.diitcenter.optovik.data.network.OptovikApi::class.java)

    @Provides
    @Singleton
    @Named("API_CATALOG")
    fun provideApiCatalog(@Named("CATALOG_RETROFIT") retrofit: Retrofit) =
        retrofit.create(ru.diitcenter.optovik.data.network.CatalogApi::class.java)

    @Provides
    @Singleton
    @Named("API_PRODUCT_CARD")
    fun provideApiProductCard(@Named("PRODUCT_CARD_RETROFIT") retrofit: Retrofit) =
        retrofit.create(ru.diitcenter.optovik.data.network.ProductCardApi::class.java)


    @Provides
    @Singleton
    @Named("API_BASKET")
    fun provideApiBasket(@Named("BASKET_RETROFIT") retrofit: Retrofit) =
        retrofit.create(ru.diitcenter.optovik.data.network.BasketApi::class.java)

    @Provides
    @Singleton
    @Named("API_LOCATION")
    fun provideApiLocation(@Named("LOCATION_RETROFIT") retrofit: Retrofit) =
        retrofit.create(ru.diitcenter.optovik.data.network.LocationApi::class.java)

    @Provides
    @Singleton
    @Named("API_MY_ORDER")
    fun provideApiMyOrder(@Named("MY_ORDER_RETROFIT") retrofit: Retrofit) =
        retrofit.create(ru.diitcenter.optovik.data.network.MyOrderApi::class.java)

    @Provides
    @Singleton
    @Named("API_NOTIFICATION")
    fun provideApiNotification(@Named("NOTIFICATION_RETROFIT") retrofit: Retrofit) =
        retrofit.create(ru.diitcenter.optovik.data.network.NotificationApi::class.java)

    @Provides
    @Singleton
    @Named("API_ORDER_INFO")
    fun provideApiOrderInfo(@Named("NOTIFICATION_RETROFIT") retrofit: Retrofit) =
        retrofit.create(ru.diitcenter.optovik.data.network.OrderInfoApi::class.java)

    companion object {
        private const val BASE_API_URL = "https://my-json-server.typicode.com"
        private const val BASE_API_URL_CATALOG = "https://raw.githubusercontent.com"
        private const val BASE_API_URL_PRODUCTCARD = "https://raw.githubusercontent.com"
        private const val BASE_API_URL_BASKET = "https://raw.githubusercontent.com"
        private const val BASE_API_URL_LOCATION = "https://raw.githubusercontent.com"
        private const val BASE_API_URL_MY_ORDER  = "https://raw.githubusercontent.com"


    }
}