package ru.diitcenter.optovik.presentation.screens.basket.mvp

import com.arellomobile.mvp.InjectViewState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import ru.diitcenter.optovik.data.global.models.Basket
import ru.terrakok.cicerone.Router
import javax.inject.Inject


@InjectViewState
class BasketPresenter @Inject constructor(
    private val router: Router,
    private val dataManager: ru.diitcenter.optovik.data.global.DataManager,
    private val basketHolder: ru.diitcenter.optovik.data.basketholder.BasketHolder
) : ru.diitcenter.optovik.presentation.global.BasePresenter<BasketView>(router, dataManager),
    ru.diitcenter.optovik.data.basketholder.BasketListener {

    override fun onFirstViewAttach() {
        basketHolder.subscribe(this)
        getBasket()
        getBasketDiliveryPrice()
    }

    override fun onUpdateBasketItems(items: MutableList<ru.diitcenter.optovik.data.basketholder.BasketHolder.Item>) {
        viewState.basketResultPriceCheсk()
        viewState.basketEmptyCheck()
        viewState.showBasket(items.map {
            Basket(
                it.product,
                it.quantity
            )
        })
    }

    fun getBasketDiliveryPrice() {
        subscriptions += dataManager.getBasket()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                { basket ->
                    viewState.showInformation(basket.priceDelivery)
                    basketHolder.synchronizeBasketWithServer()
                },
                {
                    viewState.showError()
                }
            )
    }

    fun clearBasket() {
        subscriptions += dataManager.clearBasket()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                { basket ->
                    basketHolder.clearBasketInServer{}
                },
                {
                    viewState.showError()
                }
            )
    }

    fun getBasket() {
        viewState.showBasket(basketHolder.items.map {
            Basket(
                it.product,
                it.quantity
            )
        })
    }

    fun gotoProductCard(product: ru.diitcenter.optovik.data.global.models.Product) {
        router.navigateTo(ru.diitcenter.optovik.presentation.global.Screens.ProductCard(product.id))
    }
}