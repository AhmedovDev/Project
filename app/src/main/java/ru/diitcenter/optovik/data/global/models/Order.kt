package ru.diitcenter.optovik.data.global.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class Order (
    @SerializedName("status") val status: String,
    @SerializedName("id") val id: Int,
    @SerializedName("date") val date: String,
    @SerializedName("finalPrice") val finalPrice: Int,
    @SerializedName("deliveryPrice") val deliveryPrice: Int,
    @SerializedName("priceWithOutDelivery") val priceWithOutDelivery: Int
) : Parcelable

