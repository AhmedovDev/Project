package ru.diitcenter.optovik.data.global.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class OperatorPhone(
    @SerializedName("telephone") val operatorPhone: String
    ) : Parcelable