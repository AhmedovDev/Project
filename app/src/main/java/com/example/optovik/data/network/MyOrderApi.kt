package com.example.optovik.data.network

import com.example.optovik.data.global.models.MyOrder
import io.reactivex.Single
import retrofit2.http.GET

interface MyOrderApi {
    @GET("/AhmedovDev/RoomDatabaseExample-master/master/myorder.json")
    fun getMyOrder(): Single<List<MyOrder>>
}