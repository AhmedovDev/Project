package ru.diitcenter.optovik.data.global.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class Catalog (
    @SerializedName("information") val information: String,
    @SerializedName("products") val products: List<ru.diitcenter.optovik.data.global.models.Product>
): Parcelable
