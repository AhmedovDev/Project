package ru.diitcenter.optovik.data.global.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class Checkout (
    @SerializedName("description") val description: String,
    @SerializedName("telephone") val telephone: String
) : Parcelable