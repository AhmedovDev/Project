package ru.diitcenter.optovik.presentation.screens.orderinfo.mvp

import com.arellomobile.mvp.MvpView
import ru.diitcenter.optovik.data.global.models.Basket

interface OrderInfoView : MvpView{
    fun showProgress(progress: Boolean)
    fun showProducts (basket: List<ru.diitcenter.optovik.data.global.models.Basket>)
    fun showOrderInfo(order: ru.diitcenter.optovik.data.global.models.Order, productsForOrder : List<Basket>)
    fun showError()
}