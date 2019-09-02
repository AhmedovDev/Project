package com.example.optovik.presentation.screens.basket.mvp

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.example.optovik.data.basketholder.BasketHolder
import com.example.optovik.data.basketholder.BasketListener
import com.example.optovik.data.global.DataManager
import com.example.optovik.data.global.models.Basket
import com.example.optovik.data.global.models.Product
import com.example.optovik.presentation.global.BasePresenter
import com.example.optovik.presentation.global.Screens
import com.example.optovik.presentation.global.utils.UpdateBasket
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import ru.terrakok.cicerone.Router
import javax.inject.Inject


@InjectViewState
class BasketPresenter @Inject constructor(
    private val router: Router,
    private val dataManager: DataManager,
    val updateBasket: UpdateBasket,
    private val basketHolder: BasketHolder
) : BasePresenter<BasketView>(router,dataManager), BasketListener {

    override fun onFirstViewAttach() {
        getBasket()
        basketHolder.subscribe(this)
        getBasketDiliveryPrice()
    }

    override fun onUpdateBasketItems(items: MutableList<BasketHolder.Item>) {

        viewState.showBasket(items.map { Basket(it.product,it.quantity) })
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
        viewState.showBasket(basketHolder.items.map { Basket(it.product,it.quantity) })
    }

    fun gotoProductCard(product: Product){
        router.navigateTo(Screens.ProductCard(product))
    }
}