package com.example.optovik.data.global.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class MainModel (
    @SerializedName("categories") val categoryes: List<Category>,
    @SerializedName("banners") val events: List<Event>,
    @SerializedName("lastorder") val lastOrder: MyOrder
):Parcelable
