package ru.diitcenter.optovik.data.global.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class FeedBack (
    @SerializedName("order_id") val order_id: Int,
    @SerializedName("rating") val rating: Int,
    @SerializedName("review") val review: String
) : Parcelable