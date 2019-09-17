package ru.diitcenter.optovik.data.global.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
class Notification (
    @SerializedName("image") val image: String,
    @SerializedName("time") val time: String,
    @SerializedName("date") val date: String,
    @SerializedName("information") val information: String,
    @SerializedName("product_id") val productId: Int,
    @SerializedName("order_id") val orderId: Int
) : Parcelable

