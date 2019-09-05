package ru.diitcenter.optovik.data.network

import io.reactivex.Single
import retrofit2.http.GET

interface MyOrderApi {
    @GET("/AhmedovDev/RoomDatabaseExample-master/master/myorder.json")
    fun getMyOrder(): Single<List<ru.diitcenter.optovik.data.global.models.MyOrder>>
}