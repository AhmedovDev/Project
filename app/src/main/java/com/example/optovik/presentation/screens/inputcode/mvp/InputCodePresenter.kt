package com.example.optovik.presentation.screens.inputcode.mvp

import android.database.Observable
import com.arellomobile.mvp.InjectViewState
import com.example.optovik.R
import com.example.optovik.presentation.global.BasePresenter
import com.example.optovik.presentation.global.Screens
import com.example.optovik.presentation.screens.inputphone.mvp.InputPhoneView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import ru.terrakok.cicerone.Router
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@InjectViewState
class InputCodePresenter @Inject constructor (private val router: Router) : BasePresenter<InputCodeView>(router) {

    private var time = 60
    private var rx: Disposable? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.showTimeProgress(time)
        updateTimer()
    }

    // Отсчет времени до отображкния кнопки для повторного запроса код
     fun updateTimer() {
        var firstIsVisibleSendCodeAgainFlag = true
        rx = io.reactivex.Observable.interval(1, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                time--
                if (time == 0) {
                    time = 60
                    viewState.isVisibleTimer(false)
                } else {
                    if (firstIsVisibleSendCodeAgainFlag) {
                        viewState.isVisibleTimer(true)
                    }
                    firstIsVisibleSendCodeAgainFlag = false
                    viewState.showTimeProgress(time)
                }
            }
    }

    // повторный отсчет
    fun retrySendCode() {
        rx?.dispose()
        time = 60
        updateTimer()
        viewState.showTimeProgress(time)
        viewState.isVisibleTimer(true)
    }

    fun back () {
        router.replaceScreen(Screens.InputPhone)
    }

}