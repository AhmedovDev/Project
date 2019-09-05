package ru.diitcenter.optovik.data.network

import io.reactivex.Single
import retrofit2.http.GET

interface OptovikApi {

    @GET("/AhmedovDev/DIIT24/db/")
    fun getData(): Single<ru.diitcenter.optovik.data.global.models.MainModel>



}