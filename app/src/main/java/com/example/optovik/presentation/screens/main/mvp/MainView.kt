package com.example.optovik.presentation.screens.main.mvp

import com.arellomobile.mvp.MvpView
import com.example.optovik.data.global.models.Category
import com.example.optovik.data.global.models.Event
import com.example.optovik.data.global.models.MyOrder

interface MainView : MvpView {
    fun showProgress(progress: Boolean)
    fun showCategories(category: List<Category>)
    fun showLastOrder(lastOrder: MyOrder)
    fun showError()
    fun showEvents(banner: List<Event>)
    fun visiblMain()
    fun categoryesClick()
   fun updateBasketButtonMain()
}
