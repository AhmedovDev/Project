package ru.diitcenter.optovik.presentation.screens.notofication.mvp

import com.arellomobile.mvp.InjectViewState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import ru.diitcenter.optovik.presentation.global.Screens
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.Screen
import javax.inject.Inject


@InjectViewState
class NotificationPresenter @Inject constructor(
    private val router: Router,
    private val dataManager: ru.diitcenter.optovik.data.global.DataManager,
    private var basketHolder: ru.diitcenter.optovik.data.basketholder.BasketHolder
) : ru.diitcenter.optovik.presentation.global.BasePresenter<NotificationView>(router, dataManager) {


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

    fun goToProductCard(productId: Int) {
        router.navigateTo(Screens.ProductCard(productId))
    }
}