package ru.diitcenter.optovik.presentation.screens.search.mvp

import com.arellomobile.mvp.MvpView

interface SearchView : MvpView {
    fun showProgress(progress: Boolean)
    fun showFoundProducts(products: List<ru.diitcenter.optovik.data.global.models.Product>)
    fun showError()
    fun visiblSearchList()
    fun adapterUpdate()
    fun updateBasketButtonSearch()
    fun emptyBasketCheck()
}