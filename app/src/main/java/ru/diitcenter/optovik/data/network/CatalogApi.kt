package ru.diitcenter.optovik.data.network

import io.reactivex.Single
import retrofit2.http.GET

interface CatalogApi {
    @GET("/VladimirHoldar/OptovikCatalog/master/db.json")
    fun getDataCatalog(): Single<ru.diitcenter.optovik.data.global.models.Catalog>

}