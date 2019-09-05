package ru.diitcenter.optovik.presentation.screens.inputcode.mvp

import com.arellomobile.mvp.InjectViewState
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class InputCodePresenter @Inject constructor(
    private val router: Router,
    private val dataManager: ru.diitcenter.optovik.data.global.DataManager
) :
    ru.diitcenter.optovik.presentation.global.BasePresenter<InputCodeView>(router, dataManager) {

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
        router.replaceScreen(ru.diitcenter.optovik.presentation.global.Screens.InputPhone)

    }
}

