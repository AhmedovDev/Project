package ru.diitcenter.optovik.data.global.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import io.reactivex.annotations.Nullable
import kotlinx.android.parcel.Parcelize


@Parcelize
class Notification (
    @SerializedName("type") val type: String,
    @SerializedName("time") val time: String,
    @SerializedName("date") val date: String,
    @SerializedName("title") val title: String,
    @SerializedName("information") val information: String,
    @SerializedName("targetId") val targetId: Int,
    @Nullable
    @SerializedName("order_status") val order_status: String
) : Parcelable

