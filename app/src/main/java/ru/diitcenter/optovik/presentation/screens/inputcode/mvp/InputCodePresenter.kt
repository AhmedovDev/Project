package ru.diitcenter.optovik.presentation.screens.inputcode.mvp

import com.arellomobile.mvp.InjectViewState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
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

    fun getToken(telephone: String, code: String) {

        subscriptions += dataManager.getToken(telephone, code)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                { data ->
                    viewState.saveToken(data.token)
                    viewState.goToMain()

                },
                {
                    viewState.showError()
                }
            )

    }

    fun getCode(telephone: String) {

        subscriptions += dataManager.getCode(telephone)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                { data ->
                },
                {
                    viewState.showError()
                }
            )
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

