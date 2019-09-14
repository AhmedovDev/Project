package ru.diitcenter.optovik.presentation.screens.main.mvp

import com.arellomobile.mvp.InjectViewState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class MainPresenter @Inject constructor(
    private val router: Router,
    private val dataManager: ru.diitcenter.optovik.data.global.DataManager,
    private var basketHolder: ru.diitcenter.optovik.data.basketholder.BasketHolder
) : ru.diitcenter.optovik.presentation.global.BasePresenter<MainView>(router,dataManager),
    ru.diitcenter.optovik.data.basketholder.BasketListener {

    override fun onUpdateBasketItems(items: MutableList<ru.diitcenter.optovik.data.basketholder.BasketHolder.Item>) {
        viewState.updateBasketButtonMain()
        viewState.emptyBasketCheck()
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
                data.categoryes?.let {     viewState.showCategories(data.categoryes)}
                 data.events?.let {    viewState.showEvents(data.events)}
                 data.lastOrder?.let {     viewState.showLastOrder(data.lastOrder)}
                    viewState.visiblMain()

                },
                {
                    viewState.showError()
                                    }
            )
    }
    fun onEventClick(product: ru.diitcenter.optovik.data.global.models.Product) {
        router.navigateTo(ru.diitcenter.optovik.presentation.global.Screens.ProductCard(product))
    }

    fun onSearchClick() {
        router.navigateTo(ru.diitcenter.optovik.presentation.global.Screens.Search)
    }



}