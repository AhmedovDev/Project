package ru.diitcenter.optovik.presentation.screens.splash.mvp

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import ru.diitcenter.optovik.data.prefs.PrefsHelper
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class SplashPresenter @Inject constructor(
    private val router: Router,
    private val dataManager: ru.diitcenter.optovik.data.global.DataManager,
    private var basketHolder: ru.diitcenter.optovik.data.basketholder.BasketHolder,
    private val prefsHelper: PrefsHelper
) : ru.diitcenter.optovik.presentation.global.BasePresenter<SplashView>(router,dataManager) {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        prefsHelper.run { saveToken("45ab75c1b64801374f4010cd10d8e87e") }
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