package com.example.optovik.presentation.screens.inputcode.mvp

import com.arellomobile.mvp.InjectViewState
import com.example.optovik.data.global.DataManager
import com.example.optovik.presentation.global.BasePresenter
import com.example.optovik.presentation.global.Screens
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class InputCodePresenter @Inject constructor(
    private val router: Router,
    private val dataManager: DataManager
) :
    BasePresenter<InputCodeView>(router, dataManager) {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.showTimeProgress()
        updateTimer()
    }

    fun updateTimer() {
        viewState.isVisibleTimer(true)
        viewState.showTimeProgress()
    }

    // повторный отсчет
    fun retrySendCode() {
        updateTimer()
        viewState.showTimeProgress()
        viewState.isVisibleTimer(true)
    }

    fun back() {
        router.replaceScreen(Screens.InputPhone)

    }
}

