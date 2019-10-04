package ru.diitcenter.optovik.presentation.screens.checkorder.mvp

import com.arellomobile.mvp.InjectViewState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import ru.terrakok.cicerone.Router
import javax.inject.Inject


@InjectViewState
class CheckOrderPresenter @Inject constructor(
    private val router: Router,
    private val dataManager: ru.diitcenter.optovik.data.global.DataManager,
    private val basketHolder: ru.diitcenter.optovik.data.basketholder.BasketHolder
) : ru.diitcenter.optovik.presentation.global.BasePresenter<CheckOrderView>(router, dataManager){

    fun chackOrder(description: String ,telephone: String ) {

        subscriptions += dataManager.checkOut(description, telephone)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                { data ->
                    viewState.goToMain()
                },
                {
                    viewState.showError()
                }
            )

    }


}