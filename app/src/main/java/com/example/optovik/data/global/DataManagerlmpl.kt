package com.example.optovik.data.global

import com.example.optovik.data.global.models.*
import com.example.optovik.data.network.*
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Named

class DataManagerlmpl @Inject constructor(
    @Named("API_OPTOVIK") val api: OptovikApi,
    @Named("API_CATALOG") val apiCatalog: CatalogApi,
    @Named("API_PRODUCT_CARD") val apiProductCard: ProductCardApi,
    @Named("API_BASKET") val apiBasket: BasketApi,
    @Named("API_LOCATION") val apiLocation: LocationApi,
    @Named("API_MY_ORDER") val apiMyOrder: MyOrderApi,
    @Named("API_NOTIFICATION") val apiNotification: NotificationApi,
    @Named("API_ORDER_INFO") val apiOrderInfo: OrderInfoApi


) : DataManager {

    override fun getOrderInfo(): Single<OrderInfo> =
        apiOrderInfo.getOrderInfo()
            .subscribeOn(Schedulers.io())

    override fun getNotification(): Single<List<Notification>> =
        apiNotification.getNotification()
            .subscribeOn(Schedulers.io())

    override fun getMyOrder(): Single<List<MyOrder>> =
        apiMyOrder.getMyOrder()
            .subscribeOn(Schedulers.io())

    override fun getLocation(): Single<List<Location>> =
        apiLocation.getLocation()
            .subscribeOn(Schedulers.io())


    override fun getBasket(): Single<DeliveryAndBasket> =
        apiBasket.getDeliveryAndBasket()
            .subscribeOn(Schedulers.io())


    override fun getDataCatalog(): Single<Catalog> =
        apiCatalog.getDataCatalog()
            .subscribeOn(Schedulers.io())


    override fun getMain(): Single<MainModel> =
        api.getData()
            .subscribeOn(Schedulers.io())

    override fun getProductCard(): Single<ProductCard> =
        apiProductCard.getProductCard()
            .subscribeOn(Schedulers.io())
}
