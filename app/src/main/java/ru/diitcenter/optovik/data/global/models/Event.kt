package ru.diitcenter.optovik.data.global.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class Event (
    @SerializedName("image") val image: String,
    @SerializedName("productId") val idProduct: Int
) : Parcelable