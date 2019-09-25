package ru.diitcenter.optovik.presentation.screens.myorder.mvp

import com.arellomobile.mvp.MvpView
import ru.diitcenter.optovik.data.global.models.Basket

interface MyOrderView : MvpView
{
    fun showProgress(progress: Boolean)
    fun showMyOrders(myOrder: List<ru.diitcenter.optovik.data.global.models.MyOrder>)
    fun showError()
    fun getProductsForOrder(productForOrder: List<Basket>)
}