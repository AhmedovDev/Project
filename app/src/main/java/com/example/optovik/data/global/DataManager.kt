package com.example.optovik.data.global

import com.example.optovik.data.global.models.Catalog
import com.example.optovik.data.global.models.DataModel
import io.reactivex.Single

interface DataManager {
    fun getData(): Single<DataModel>

    fun getDataCatalog(): Single<Catalog>

}