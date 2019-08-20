package com.example.optovik.data.global.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class Location(
    @SerializedName("adres") val adres: String,
    @SerializedName("phone") val phone: String
) : Parcelable