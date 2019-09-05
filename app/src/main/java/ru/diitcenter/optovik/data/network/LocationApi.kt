package ru.diitcenter.optovik.data.network

import io.reactivex.Single
import retrofit2.http.GET

interface LocationApi {
    @GET("/AhmedovDev/RoomDatabaseExample-master/master/db.json")
    fun getLocation(): Single<List<ru.diitcenter.optovik.data.global.models.Location>>

}
