package com.example.optovik.data.global.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class DataModel (
    @SerializedName("categories") val categoryes: List<Category>,
    @SerializedName("banners") val events: List<Event>
):Parcelable
