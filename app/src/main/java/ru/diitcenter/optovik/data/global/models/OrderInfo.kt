package ru.diitcenter.optovik.data.global.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class OrderInfo (
    @SerializedName("items") val basket: List<ru.diitcenter.optovik.data.global.models.Basket>,
    @SerializedName("order") val orderInfo: ru.diitcenter.optovik.data.global.models.Order

): Parcelable