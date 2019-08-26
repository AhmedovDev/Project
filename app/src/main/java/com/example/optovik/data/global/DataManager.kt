package com.example.optovik.data.global

import com.example.optovik.data.global.models.*
import io.reactivex.Single

interface DataManager {
    fun getData(): Single<DataModel>

    fun getDataCatalog(): Single<Catalog>

    fun getProducCard(): Single<ProductCard>

    fun getBasket(): Single<DeliveryAndBasket>

    fun getLocation(): Single<List<Location>>

    fun getMyOrder(): Single<List<MyOrder>>

}