package ru.diitcenter.optovik.presentation.screens.splash.mvp

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
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
) : ru.diitcenter.optovik.presentation.global.BasePresenter<SplashView>(router, dataManager) {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        //   prefsHelper.run { saveToken("ab076d6dbd4dbf0990864325d41ea41e") }
        getBasket()
    }

    fun getBasket() {
        subscriptions += dataManager.getBasket()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                { data ->
                    data.basket?.let {
                        //viewState.getBasket(data.basket)
                        viewState.goToMain()
                    }
                    viewState.goToMain()
                },
                { error ->

                    when ((error as HttpException).code()) {
                        401 -> {
                            viewState.goToAutorization() // Токен истек или не существует
                        }
                        else -> viewState.showError()
                    }

                }
            )
    }

    fun setPushToken(token: String, deviseId: String) {
        subscriptions += dataManager.setPushToken(token, deviseId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                { data ->
                }
                ,
                { error ->
                    viewState.showError()

                }
            )
    }
}