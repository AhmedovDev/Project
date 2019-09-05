package ru.diitcenter.optovik.data.global.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class MyOrder (
    @SerializedName("status") val title: String,
    @SerializedName("id") val id: Int,
    @SerializedName("date") val date: String,
    @SerializedName("sum") val sum: Int
) : Parcelable

