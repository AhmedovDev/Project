package com.example.optovik.presentation.global.utils

import io.reactivex.subjects.PublishSubject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UpdateBasket @Inject constructor() {
    val tracker = PublishSubject.create<Pair<Int,Int>>()

    fun notify1(price : Pair<Int,Int>)  {
        tracker.onNext(price)
    }

    fun subscribe() = tracker
}