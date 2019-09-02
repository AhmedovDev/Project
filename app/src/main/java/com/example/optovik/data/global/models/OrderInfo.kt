package com.example.optovik.data.global.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class OrderInfo (
    @SerializedName("items") val basket: List<Basket>,
    @SerializedName("order") val orderInfo: Order

): Parcelable