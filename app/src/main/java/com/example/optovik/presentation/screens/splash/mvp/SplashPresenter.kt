package com.example.optovik.presentation.screens.splash.mvp

import com.arellomobile.mvp.InjectViewState
import com.example.optovik.data.basketholder.BasketHolder
import com.example.optovik.data.global.DataManager
import com.example.optovik.presentation.global.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class SplashPresenter @Inject constructor(
    private val router: Router,
    private val dataManager: DataManager,
    private var basketHolder: BasketHolder
) : BasePresenter<SplashView>(router,dataManager) {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        getBasket()
    }

    fun getBasket() {
        subscriptions += dataManager.getBasket()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                { basket ->
                    basketHolder.items = basket.basket.map { BasketHolder.Item(it.product,it.quantity) } as MutableList
                    viewState.intent()

                },
                {
                   viewState.showError()
                }
            )
    }
}