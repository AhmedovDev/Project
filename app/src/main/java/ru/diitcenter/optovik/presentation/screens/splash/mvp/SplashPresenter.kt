package ru.diitcenter.optovik.presentation.screens.splash.mvp

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class SplashPresenter @Inject constructor(
    private val router: Router,
    private val dataManager: ru.diitcenter.optovik.data.global.DataManager,
    private var basketHolder: ru.diitcenter.optovik.data.basketholder.BasketHolder
) : ru.diitcenter.optovik.presentation.global.BasePresenter<SplashView>(router,dataManager) {

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
                    Log.e("BASKET: ","$basket")
                    basketHolder.items = basket.basket.map { ru.diitcenter.optovik.data.basketholder.BasketHolder.Item(it.product,it.quantity) } as MutableList
                    viewState.intent()

                },
                {
                   viewState.showError()
                }
            )
    }
}