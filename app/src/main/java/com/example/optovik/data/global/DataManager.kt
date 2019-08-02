package com.example.optovik.data.global

import com.example.optovik.data.global.models.Basket
import com.example.optovik.data.global.models.Catalog
import com.example.optovik.data.global.models.DataModel
import com.example.optovik.data.global.models.ProductCard
import io.reactivex.Single

interface DataManager {
    fun getData(): Single<DataModel>

    fun getDataCatalog(): Single<Catalog>

    fun getProducCard(): Single<ProductCard>

    fun getBasket(): Single<List<Basket>>

}