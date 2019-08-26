package com.example.optovik.presentation.screens.myorder.mvp

import com.arellomobile.mvp.MvpView
import com.example.optovik.data.global.models.MyOrder

interface MyOrderView : MvpView
{
    fun showProgress(progress: Boolean)
    fun showMyOrders(myOrder: List<MyOrder>)
    fun showError()
}