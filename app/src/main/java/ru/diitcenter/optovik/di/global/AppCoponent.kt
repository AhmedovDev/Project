package ru.diitcenter.optovik.di.global

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        ru.diitcenter.optovik.di.global.modules.NetworkModule::class,
        ru.diitcenter.optovik.di.global.modules.NavigationModule::class,
        ru.diitcenter.optovik.di.global.modules.PrefsModule::class,
        ru.diitcenter.optovik.di.global.modules.DataModule::class,
        ru.diitcenter.optovik.di.global.modules.DatabaseModule::class,
        ru.diitcenter.optovik.di.global.modules.BasketModule::class

    ]
)
@Singleton
interface AppComponent {
    fun autorizationComponentBuilder(): ru.diitcenter.optovik.di.screens.autorization.AutorizationComponent.Builder
    fun inputPhoneComponentBuilder(): ru.diitcenter.optovik.di.screens.inputphone.InputPhoneComponent.Builder
    fun inputCodeComponentBuilder(): ru.diitcenter.optovik.di.screens.inputcode.InputCodeComponent.Builder
    fun mainComponentBuilder(): ru.diitcenter.optovik.di.screens.main.MainComponent.Builder
    fun catalogComponentBuilder(): ru.diitcenter.optovik.di.screens.catalog.CatalogComponent.Builder
    fun productCardComponentBuilder(): ru.diitcenter.optovik.di.screens.productcard.ProductCardComponent.Builder
    fun basketComponentBuilder(): ru.diitcenter.optovik.di.screens.basket.BasketComponent.Builder
    fun dialogBasketComponentBuilder(): ru.diitcenter.optovik.di.screens.dialogbasket.DialogBasketComponent.Builder
    fun splashComponentBuilder(): ru.diitcenter.optovik.di.screens.splash.SplashComponent.Builder
    fun searchComponentBuilder(): ru.diitcenter.optovik.di.screens.search.SearchComponent.Builder
    fun adresbookComponentBuilder(): ru.diitcenter.optovik.di.screens.adresbook.AdresbookComponent.Builder
    fun myOrderComponentBuilder(): ru.diitcenter.optovik.di.screens.myorder.MyOrderComponent.Builder
    fun notificationComponentBuilder(): ru.diitcenter.optovik.di.screens.notification.NotificationComponent.Builder
    fun checkOrderComponentBuilder(): ru.diitcenter.optovik.di.screens.checkorder.CheckOrderComponent.Builder
    fun orderInfoComponentBuilder(): ru.diitcenter.optovik.di.screens.orderinfo.OrderInfoComponent.Builder

    @Component.Builder
    interface Builder {

        fun build(): ru.diitcenter.optovik.di.global.AppComponent

        @BindsInstance
        fun bindsInstanceContext(context: Context): ru.diitcenter.optovik.di.global.AppComponent.Builder
    }
}
