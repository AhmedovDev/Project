package com.example.optovik.presentation.screens.basket.mvp

import com.arellomobile.mvp.MvpView
import com.example.optovik.data.global.models.Basket

interface BasketView : MvpView {
    fun showProgress(progress: Boolean)
    fun showBasket(basket: List<Basket>)
    fun showInformation(information: String)
    fun showError()
    fun visiblBasket()
}
