package ru.diitcenter.optovik.presentation.screens.main.mvp

import com.arellomobile.mvp.MvpView

interface MainView : MvpView {
    fun showProgress(progress: Boolean)
    fun showCategories(category: List<ru.diitcenter.optovik.data.global.models.Category>)
    fun showLastOrder(lastOrder: ru.diitcenter.optovik.data.global.models.MyOrder)
    fun showError()
    fun showEvents(banner: List<ru.diitcenter.optovik.data.global.models.Event>)
    fun visiblMain()
    fun categoryesClick()
   fun updateBasketButtonMain()
  fun  emptyBasketCheck()
}
