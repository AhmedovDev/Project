package com.example.optovik.data.global

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DataManagerlmpl @Inject constructor(
    private val api: GitHubApi
) : DataManager {

    override fun getUsers(): Single<List<User>> =
        api.getUsers()
            .subscribeOn(Schedulers.io())


}
