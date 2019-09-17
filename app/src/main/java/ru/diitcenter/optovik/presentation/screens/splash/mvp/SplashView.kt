package ru.diitcenter.optovik.presentation.screens.splash.mvp

import com.arellomobile.mvp.MvpView
import ru.diitcenter.optovik.data.global.models.Basket

interface SplashView : MvpView {
    fun showError()
    fun getBasket(basket : List<Basket>)
    fun goToMain()
    fun goToAutorization()
}

