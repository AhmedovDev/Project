package com.example.optovik.presentation.screens.catalog.mvp

import com.arellomobile.mvp.MvpView
import com.example.optovik.data.global.models.Category
import com.example.optovik.data.global.models.Products

interface CatalogView : MvpView {
    fun showProgress(progress: Boolean)
    fun showProducts(products: List<Products>)
    fun showInformation(information: String)
    fun showError()
    fun visiblCatalog()
}