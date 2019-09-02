package com.example.optovik.data.global

import com.example.optovik.data.global.models.*
import io.reactivex.Single

interface DataManager {
    fun getMain(): Single<MainModel>

    fun getDataCatalog(): Single<Catalog>

    fun getProductCard(): Single<ProductCard>

    fun getBasket(): Single<DeliveryAndBasket>

    fun getLocation(): Single<List<Location>>

    fun getMyOrder(): Single<List<MyOrder>>

    fun getNotification(): Single<List<Notification>>

    fun getOrderInfo(): Single<OrderInfo>

}