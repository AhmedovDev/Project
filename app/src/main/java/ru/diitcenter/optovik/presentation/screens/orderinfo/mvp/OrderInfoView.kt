package ru.diitcenter.optovik.presentation.screens.orderinfo.mvp

import com.arellomobile.mvp.MvpView

interface OrderInfoView : MvpView{
    fun showProgress(progress: Boolean)
    fun showProducts (basket: List<ru.diitcenter.optovik.data.global.models.Basket>)
    fun showOrderInfo(order: ru.diitcenter.optovik.data.global.models.Order)
    fun showError()
}