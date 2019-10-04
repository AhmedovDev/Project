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
     //   getBasket()
    }

    fun getBasket() {
        subscriptions += dataManager.getBasket()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                { data ->
                    data.basket?.let {
                        viewState.goToMain()
                    }
                        viewState.goToMain()
                },
                { error ->
                    Log.e("ERROR_Exception: ", "$error")
                    if (error is HttpException) {
                        when (error.code()) {
                            401 -> {
                                viewState.goToAutorization() // Токен истек или не существует
                            }
                            500 -> {
                                viewState.goToMain()
                            }
                            else -> viewState.showError()
                        }
                    }
                    else viewState.showError()


                }
            )
    }

    fun setPushToken(token: String, deviceId: String) {
        subscriptions += dataManager.setPushToken(token, deviceId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                        data ->
                }
                ,
                { error ->
                    viewState.showError()

                }
            )
    }
}