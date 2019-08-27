package com.example.optovik.data.network

import com.example.optovik.data.global.models.Notification
import io.reactivex.Single
import retrofit2.http.GET

interface NotificationApi {
    @GET("/AhmedovDev/RoomDatabaseExample-master/master/notification.json")
    fun getNotification(): Single<List<Notification>>

}