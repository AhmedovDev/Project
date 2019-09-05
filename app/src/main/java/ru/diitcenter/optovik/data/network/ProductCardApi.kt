package ru.diitcenter.optovik.data.network

import io.reactivex.Single
import retrofit2.http.GET

interface ProductCardApi {
    @GET("/AhmedovDev/ClearCity/master/db.json")
    fun getProductCard(): Single<ru.diitcenter.optovik.data.global.models.ProductCard>
}