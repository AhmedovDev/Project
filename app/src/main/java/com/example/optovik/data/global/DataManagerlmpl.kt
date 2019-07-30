package com.example.optovik.data.global

import com.example.optovik.data.global.models.Catalog
import com.example.optovik.data.global.models.DataModel
import com.example.optovik.data.global.models.ProductCard
import com.example.optovik.data.network.CatalogApi
import com.example.optovik.data.network.OptovikApi
import com.example.optovik.data.network.ProductCardApi
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Named

class DataManagerlmpl @Inject constructor(
    @Named("API_OPTOVIK") val api: OptovikApi,
    @Named("API_CATALOG") val apiCatalog: CatalogApi,
    @Named("API_PRODUCT_CARD")val apiProductCard: ProductCardApi

) : DataManager {
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
