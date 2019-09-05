package ru.diitcenter.optovik.presentation.screens.orderinfo.mvp

import com.arellomobile.mvp.InjectViewState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class OrderInfoPresenter @Inject constructor(
    private val router: Router,
    private val dataManager: ru.diitcenter.optovik.data.global.DataManager,
    private var basketHolder: ru.diitcenter.optovik.data.basketholder.BasketHolder
) : ru.diitcenter.optovik.presentation.global.BasePresenter<OrderInfoView>(router,dataManager) {



    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        getOrderInfo()
    }

    fun getOrderInfo() {
        subscriptions += dataManager.getOrderInfo()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnSubscribe { viewState.showProgress(true) }
            .doAfterTerminate { viewState.showProgress(false) }
            .subscribe(
                { data ->
                    viewState.showOrderInfo(data.orderInfo)
                    viewState.showProducts(data.basket)

                },
                {
                    viewState.showError()
                }
            )
    }




}