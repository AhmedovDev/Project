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
    @Named("API_MY_ORDER") val apiMyOrder: MyOrderApi

) : DataManager {

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


    override fun getData(): Single<DataModel> =
        api.getData()
            .subscribeOn(Schedulers.io())

    override fun getProducCard(): Single<ProductCard> =
        apiProductCard.getProductCard()
            .subscribeOn(Schedulers.io())
}
