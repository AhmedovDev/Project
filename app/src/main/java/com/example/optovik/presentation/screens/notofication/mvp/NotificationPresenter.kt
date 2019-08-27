package com.example.optovik.presentation.screens.notofication.mvp

import com.arellomobile.mvp.InjectViewState
import com.example.optovik.data.basketholder.BasketHolder
import com.example.optovik.data.global.DataManager
import com.example.optovik.presentation.global.BasePresenter
import com.example.optovik.presentation.screens.myorder.mvp.MyOrderView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import ru.terrakok.cicerone.Router
import javax.inject.Inject


@InjectViewState
class NotificationPresenter @Inject constructor(
    private val router: Router,
    private val dataManager: DataManager,
    private var basketHolder: BasketHolder
) : BasePresenter<NotificationView>(router, dataManager) {


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        getNotifications()
    }

    fun getNotifications() {
        subscriptions += dataManager.getNotification()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnSubscribe { viewState.showProgress(true) }
            .doAfterTerminate { viewState.showProgress(false) }
            .subscribe(
                { data ->
                    data
                    viewState.showNotification(data)
                },
                {
                    viewState.showError()
                }
            )
    }
}