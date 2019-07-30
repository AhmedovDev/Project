package com.example.optovik.data.network

import com.example.optovik.data.global.models.Catalog
import com.example.optovik.data.global.models.ProductCard
import io.reactivex.Single
import retrofit2.http.GET

interface ProductCardApi {
    @GET("/AhmedovDev/ClearCity/master/db.json")
    fun getProductCard(): Single<ProductCard>
}