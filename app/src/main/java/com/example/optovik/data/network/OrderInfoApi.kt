package com.example.optovik.data.network

import com.example.optovik.data.global.models.OrderInfo
import io.reactivex.Single
import retrofit2.http.GET

interface OrderInfoApi {

    @GET("/AhmedovDev/RoomDatabaseExample-master/master/orderinfo.json")
    fun getOrderInfo(): Single<OrderInfo>



}