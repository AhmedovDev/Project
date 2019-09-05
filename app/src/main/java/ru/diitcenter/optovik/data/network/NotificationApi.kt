package ru.diitcenter.optovik.data.network

import io.reactivex.Single
import retrofit2.http.GET

interface NotificationApi {
    @GET("/AhmedovDev/RoomDatabaseExample-master/master/notification.json")
    fun getNotification(): Single<List<ru.diitcenter.optovik.data.global.models.Notification>>

}