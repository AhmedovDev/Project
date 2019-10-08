package ru.diitcenter.optovik.data.global.models

import kotlinx.android.parcel.Parcelize

@Parcelize
class PushToken(
    @SerializedName("") val operatorPhone: String,
) : Parcelable