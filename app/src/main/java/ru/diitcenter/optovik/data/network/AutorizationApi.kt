package ru.diitcenter.optovik.data.network

import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query
import ru.diitcenter.optovik.data.global.models.Autorization

interface AutorizationApi {

    @POST("/api/v1/login")
       fun getCode(
        @Query("telephone") telephone: String
    ): Single<Autorization>
}