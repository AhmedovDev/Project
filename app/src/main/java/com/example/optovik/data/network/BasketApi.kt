package com.example.optovik.data.network

import com.example.optovik.data.global.models.Basket
import com.example.optovik.data.global.models.Catalog
import io.reactivex.Single
import retrofit2.http.GET

interface BasketApi {
    @GET("/VladimirHoldar/OptovikQuantity/master/db.json")
    fun getBasket(): Single<List<Basket>>

}