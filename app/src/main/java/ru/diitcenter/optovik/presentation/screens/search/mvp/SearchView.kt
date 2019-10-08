package ru.diitcenter.optovik.presentation.screens.search.mvp

import com.arellomobile.mvp.MvpView
import ru.diitcenter.optovik.data.global.models.Product

interface SearchView : MvpView {
    fun showProgress(progress: Boolean)
    fun showFoundProducts(products: List<Product>)
    fun notFound()
    fun visiblSearchList()
   // fun adapterUpdate()
    fun updateBasketButtonSearch()
    fun emptyBasketCheck()
}