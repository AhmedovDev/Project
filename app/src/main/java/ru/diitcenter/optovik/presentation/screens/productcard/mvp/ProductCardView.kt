package ru.diitcenter.optovik.presentation.screens.productcard.mvp

import com.arellomobile.mvp.MvpView

interface ProductCardView : MvpView{
    fun showProgress(progress: Boolean)
    fun showError()
    fun visiblProductCard()
    fun showProductCardImages(productCard: ru.diitcenter.optovik.data.global.models.ProductCard)
    fun showProductCardInformation(productCard: ru.diitcenter.optovik.data.global.models.ProductCard)
    fun updateBasketButton()
}