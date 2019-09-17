package ru.diitcenter.optovik.data.global.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class PushToken(
    @SerializedName("token") val token: String,
    @SerializedName("device_id") val device_id: String
) : Parcelable