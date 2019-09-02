package com.example.optovik.presentation.screens.orderinfo.mvp

import com.arellomobile.mvp.MvpView
import com.example.optovik.data.global.models.Basket
import com.example.optovik.data.global.models.Order

interface OrderInfoView : MvpView{
    fun showProgress(progress: Boolean)
    fun showProducts (basket: List<Basket>)
    fun showOrderInfo(order: Order)
    fun showError()
}