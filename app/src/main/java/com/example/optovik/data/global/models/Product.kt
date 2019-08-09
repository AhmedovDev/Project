package com.example.optovik.data.global.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class Product (
    @SerializedName("image") val image: String,
    @SerializedName("id") val id: Int,
    @SerializedName("title") val name: String,
    @SerializedName("price") val price: Int,
    @SerializedName("count") val count: String,
    @SerializedName("isEstimatedPrice") val isEstimatedPrice: Boolean,
    @SerializedName("presence") val presence: Boolean,
    var quantity: Int?

) : Parcelable