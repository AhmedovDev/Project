package ru.diitcenter.optovik.presentation.screens.basket.mvp

import com.arellomobile.mvp.MvpView

interface BasketView : MvpView {
    fun showProgress(progress: Boolean)
    fun showBasket(basket: List<ru.diitcenter.optovik.data.global.models.Basket>)
    fun showInformation(information: String)
    fun showError()
    fun visiblBasket()
}
