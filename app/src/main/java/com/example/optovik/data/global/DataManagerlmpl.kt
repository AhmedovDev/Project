package com.example.optovik.data.global

import com.example.optovik.data.global.models.DataModel
import com.example.optovik.data.network.OptovikApi
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DataManagerlmpl @Inject constructor(
    private val api: OptovikApi
) : DataManager {

    override fun getData(): Single<DataModel> =
        api.getData()
            .subscribeOn(Schedulers.io())
}
