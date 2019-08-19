package com.example.optovik.data.global.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class DeliveryAndBasket (
    @SerializedName("priceDelivery") val priceDelivery: String,
    @SerializedName("items") val basket: List<Basket>
): Parcelable