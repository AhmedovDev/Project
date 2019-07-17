package com.example.optovik.data.global

import io.reactivex.Single

interface DataManager {
    fun getUsers(): Single<List<User>>
}