package com.example.optovik.presentation.screens.search.mvp

import com.arellomobile.mvp.MvpView
import com.example.optovik.data.global.models.Product

interface SearchView : MvpView {
    fun showProgress(progress: Boolean)
    fun showFoundProducts(products: List<Product>)
    fun showError()
    fun visiblSearchList()
    fun adapterUpdate()
}