package com.example.optovik.presentation.screens.catalog.mvp

import com.arellomobile.mvp.MvpView
import com.example.optovik.data.global.models.Product

interface CatalogView : MvpView {
    fun showProgress(progress: Boolean)
    fun showProducts(products: List<Product>)
    fun showInformation(information: String)
    fun showError()
    fun visiblCatalog()
    fun adapterUpdate()
    fun updateBasketButton()
}