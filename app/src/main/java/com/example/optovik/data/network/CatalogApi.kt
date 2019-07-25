package com.example.optovik.data.network

import com.example.optovik.data.global.models.Catalog
import io.reactivex.Single
import retrofit2.http.GET

interface CatalogApi {
    @GET("/VladimirHoldar/OptovikCatalog/master/db.json")
    fun getDataCatalog(): Single<Catalog>

}