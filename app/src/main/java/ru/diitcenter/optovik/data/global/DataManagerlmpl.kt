package ru.diitcenter.optovik.data.global


import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Named

class DataManagerlmpl @Inject constructor(
    @Named("API_OPTOVIK") val api: ru.diitcenter.optovik.data.network.OptovikApi,
    @Named("API_CATALOG") val apiCatalog: ru.diitcenter.optovik.data.network.CatalogApi,
    @Named("API_PRODUCT_CARD") val apiProductCard: ru.diitcenter.optovik.data.network.ProductCardApi,
    @Named("API_BASKET") val apiBasket: ru.diitcenter.optovik.data.network.BasketApi,
    @Named("API_LOCATION") val apiLocation: ru.diitcenter.optovik.data.network.LocationApi,
    @Named("API_MY_ORDER") val apiMyOrder: ru.diitcenter.optovik.data.network.MyOrderApi,
    @Named("API_NOTIFICATION") val apiNotification: ru.diitcenter.optovik.data.network.NotificationApi,
    @Named("API_ORDER_INFO") val apiOrderInfo: ru.diitcenter.optovik.data.network.OrderInfoApi


) : ru.diitcenter.optovik.data.global.DataManager {

    override fun getOrderInfo(): Single<ru.diitcenter.optovik.data.global.models.OrderInfo> =
        apiOrderInfo.getOrderInfo()
            .subscribeOn(Schedulers.io())

    override fun getNotification(): Single<List<ru.diitcenter.optovik.data.global.models.Notification>> =
        apiNotification.getNotification()
            .subscribeOn(Schedulers.io())

    override fun getMyOrder(): Single<List<ru.diitcenter.optovik.data.global.models.MyOrder>> =
        apiMyOrder.getMyOrder()
            .subscribeOn(Schedulers.io())

    override fun getLocation(): Single<List<ru.diitcenter.optovik.data.global.models.Location>> =
        apiLocation.getLocation()
            .subscribeOn(Schedulers.io())


    override fun getBasket(): Single<ru.diitcenter.optovik.data.global.models.DeliveryAndBasket> =
        apiBasket.getDeliveryAndBasket()
            .subscribeOn(Schedulers.io())


    override fun getDataCatalog(): Single<ru.diitcenter.optovik.data.global.models.Catalog> =
        apiCatalog.getDataCatalog()
            .subscribeOn(Schedulers.io())


    override fun getMain(): Single<ru.diitcenter.optovik.data.global.models.MainModel> =
        api.getData()
            .subscribeOn(Schedulers.io())

    override fun getProductCard(): Single<ru.diitcenter.optovik.data.global.models.ProductCard> =
        apiProductCard.getProductCard()
            .subscribeOn(Schedulers.io())
}
