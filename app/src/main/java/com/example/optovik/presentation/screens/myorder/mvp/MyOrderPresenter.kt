package com.example.optovik.presentation.screens.myorder.mvp

import com.arellomobile.mvp.InjectViewState
import com.example.optovik.data.basketholder.BasketHolder
import com.example.optovik.data.basketholder.BasketListener
import com.example.optovik.data.global.DataManager
import com.example.optovik.presentation.global.BasePresenter
import com.example.optovik.presentation.screens.main.mvp.MainView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class MyOrderPresenter @Inject constructor(
    private val router: Router,
    private val dataManager: DataManager,
    private var basketHolder: BasketHolder
) : BasePresenter<MyOrderView>(router,dataManager) {



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