package ru.diitcenter.optovik.data.global.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class Category (
    @SerializedName("image") val image: String,
    @SerializedName("categoryId") val id: Int,
    @SerializedName("name") val name: String

) : Parcelable