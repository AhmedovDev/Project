package com.example.optovik.presentation.screens.main.mvp

import com.arellomobile.mvp.InjectViewState
import com.example.optovik.data.basketholder.BasketHolder
import com.example.optovik.data.basketholder.BasketListener
import com.example.optovik.data.global.DataManager
import com.example.optovik.data.global.models.Product
import com.example.optovik.presentation.global.BasePresenter
import com.example.optovik.presentation.global.Screens
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class MainPresenter @Inject constructor(
    private val router: Router,
    private val dataManager: DataManager,
    private var basketHolder: BasketHolder
) : BasePresenter<MainView>(router,dataManager), BasketListener {

    override fun onUpdateBasketItems(items: MutableList<BasketHolder.Item>) {
        viewState.updateBasketButtonMain()
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        getAllData()
        basketHolder.subscribe(this)

    }



    fun getAllData() {
        subscriptions += dataManager.getMain()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnSubscribe { viewState.showProgress(true) }
            .doAfterTerminate { viewState.showProgress(false) }
            .subscribe(
                { data ->
                    viewState.showCategories(data.categoryes)
                    viewState.showEvents(data.events)
                    viewState.showLastOrder(data.lastOrder)
                    viewState.visiblMain()

                },
                {
                    viewState.showError()
                                    }
            )
    }
    fun onEventClick(product: Product) {
        router.navigateTo(Screens.ProductCard(product))
    }

    fun onSearchClick() {
        router.navigateTo(Screens.Search)
    }



}