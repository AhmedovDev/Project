package ru.diitcenter.optovik.presentation.screens.catalog.mvp

import com.arellomobile.mvp.MvpView

interface CatalogView : MvpView {
    fun showProgress(progress: Boolean)
    fun showProducts(products: List<ru.diitcenter.optovik.data.global.models.Product>)
    fun showInformation(information: String)
    fun showError()
    fun visiblCatalog()
    fun adapterUpdate()
    fun updateBasketButton()
    fun emptyBasketCheck()


}