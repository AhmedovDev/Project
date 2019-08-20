package com.example.optovik.data.network

import com.example.optovik.data.global.models.Catalog
import com.example.optovik.data.global.models.Location
import io.reactivex.Single
import retrofit2.http.GET

interface LocationApi {

    @GET("/AhmedovDev/RoomDatabaseExample-master/master/db.json")
    fun getLocation(): Single<List<Location>>

}
