package ru.diitcenter.optovik.data.network

import io.reactivex.Single
import retrofit2.http.GET

interface OrderInfoApi {

    @GET("/AhmedovDev/RoomDatabaseExample-master/master/orderinfo.json")
    fun getOrderInfo(): Single<ru.diitcenter.optovik.data.global.models.OrderInfo>



}