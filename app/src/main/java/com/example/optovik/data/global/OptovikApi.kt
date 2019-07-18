package com.example.optovik.data.global

import com.example.optovik.data.global.models.DataModel
import io.reactivex.Single
import retrofit2.http.GET

interface OptovikApi {

    @GET("/AhmedovDev/DIIT24/db/")
    fun getData(): Single<DataModel>



}