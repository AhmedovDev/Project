package ru.diitcenter.optovik.presentation.screens.inputphone.mvp

import com.arellomobile.mvp.InjectViewState
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class InputPhonePresenter @Inject constructor(
    private val router: Router,
    private val dataManager: ru.diitcenter.optovik.data.global.DataManager
) : ru.diitcenter.optovik.presentation.global.BasePresenter<InputPhoneView>(router, dataManager) {

    fun getCode(telephone: String) {

            subscriptions += dataManager.getCode(telephone)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe { viewState.showProgress(true) }
                .doAfterTerminate { viewState.showProgress(false) }
                .subscribe(
                    { data ->
                       viewState.goToInputCode()

                    },
                    {
                        when ((it as HttpException).code()) {
                            404 -> {   // если пользователя нет в системе
                             //   viewState.goToAutorization() // Токен истек или не существует
                            }
                            else ->viewState.showError()
                        }
                    }
                )
        }


    fun gotoInputCode(phone: String) {
        router.replaceScreen(ru.diitcenter.optovik.presentation.global.Screens.InputCode(phone))
    }
}