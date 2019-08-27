package com.example.optovik.data.network

import com.example.optovik.data.global.models.MainModel
import io.reactivex.Single
import retrofit2.http.GET

interface OptovikApi {

    @GET("/AhmedovDev/DIIT24/db/")
    fun getData(): Single<MainModel>



}