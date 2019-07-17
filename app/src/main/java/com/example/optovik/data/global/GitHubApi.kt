package com.example.optovik.data.global

import io.reactivex.Single
import retrofit2.http.GET

interface GitHubApi {

    @GET("users")
    fun getUsers(): Single<List<User>>

}