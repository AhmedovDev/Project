package ru.diitcenter.optovik.presentation.screens.myorder.mvp

import com.arellomobile.mvp.InjectViewState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class MyOrderPresenter @Inject constructor(
    private val router: Router,
    private val dataManager: ru.diitcenter.optovik.data.global.DataManager,
    private var basketHolder: ru.diitcenter.optovik.data.basketholder.BasketHolder
) : ru.diitcenter.optovik.presentation.global.BasePresenter<MyOrderView>(router,dataManager) {



    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        getMyOrders()
    }



    fun getMyOrders() {
        subscriptions += dataManager.getMyOrder()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnSubscribe { viewState.showProgress(true) }
            .doAfterTerminate { viewState.showProgress(false) }
            .subscribe(
                { data ->
                    data
                    viewState.showMyOrders(data)
                },
                {
                    viewState.showError()
                }
            )
    }
}