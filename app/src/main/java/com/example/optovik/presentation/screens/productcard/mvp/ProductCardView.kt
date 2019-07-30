package com.example.optovik.presentation.screens.productcard.mvp

import com.arellomobile.mvp.MvpView
import com.example.optovik.data.global.models.ProductCard

interface ProductCardView : MvpView{
    fun showProgress(progress: Boolean)
    fun showError()
    fun visiblProductCard()
    fun showProductCardImages(productCard: ProductCard)
    fun showProductCardInformation(productCard: ProductCard)
}