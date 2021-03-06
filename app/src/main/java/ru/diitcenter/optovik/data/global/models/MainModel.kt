package ru.diitcenter.optovik.data.global.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class MainModel (
    @SerializedName("categories") val categoryes: List<ru.diitcenter.optovik.data.global.models.Category>,
    @SerializedName("banners") val events: List<ru.diitcenter.optovik.data.global.models.Event>,
    @SerializedName("lastorder") val lastOrder: ru.diitcenter.optovik.data.global.models.MyOrder
):Parcelable
