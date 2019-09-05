package ru.diitcenter.optovik.data.network

import io.reactivex.Single
import retrofit2.http.GET

interface BasketApi {
    @GET("/VladimirHoldar/OptovikQuantity/master/db.json")
    fun getDeliveryAndBasket(): Single<ru.diitcenter.optovik.data.global.models.DeliveryAndBasket>

}