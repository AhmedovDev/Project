package com.example.optovik.data.global.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class ProductCard (
    @SerializedName("image") val images: List<String>,
    @SerializedName("id") val id: Int,
    @SerializedName("price") val price: Int,
    @SerializedName("count") val count: String,
    @SerializedName("isEstimatedPrice") val isEstimatedPrice: Boolean,
    @SerializedName("presence") val presence: Boolean,
    @SerializedName("description") val description: String,
    @SerializedName("title") val title: String

) : Parcelable