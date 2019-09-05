package ru.diitcenter.optovik.presentation.screens.basket.mvp

import com.arellomobile.mvp.InjectViewState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import ru.terrakok.cicerone.Router
import javax.inject.Inject


@InjectViewState
class BasketPresenter @Inject constructor(
    private val router: Router,
    private val dataManager: ru.diitcenter.optovik.data.global.DataManager,
    private val basketHolder: ru.diitcenter.optovik.data.basketholder.BasketHolder
) : ru.diitcenter.optovik.presentation.global.BasePresenter<BasketView>(router,dataManager),
    ru.diitcenter.optovik.data.basketholder.BasketListener {

    override fun onFirstViewAttach() {
        getBasket()
        basketHolder.subscribe(this)
        getBasketDiliveryPrice()
    }

    override fun onUpdateBasketItems(items: MutableList<ru.diitcenter.optovik.data.basketholder.BasketHolder.Item>) {

        viewState.showBasket(items.map {
            ru.diitcenter.optovik.data.global.models.Basket(
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
                },
                {
                    viewState.showError()
                }
            )
    }

    fun getBasket(){
        viewState.showBasket(basketHolder.items.map {
            ru.diitcenter.optovik.data.global.models.Basket(
                it.product,
                it.quantity
            )
        })
    }

    fun gotoProductCard(product: ru.diitcenter.optovik.data.global.models.Product){
        router.navigateTo(ru.diitcenter.optovik.presentation.global.Screens.ProductCard(product))
    }
}