package com.example.optovik.presentation.screens.catalog.mvp

import com.arellomobile.mvp.InjectViewState
import com.example.optovik.data.global.DataManager
import com.example.optovik.data.global.models.Basket
import com.example.optovik.data.global.models.Products
import com.example.optovik.presentation.global.BasePresenter
import com.example.optovik.presentation.global.Screens
import com.example.optovik.presentation.global.utils.NetworkChecking
import com.example.optovik.presentation.global.utils.UpdateBasket
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.PublishSubject
import ru.terrakok.cicerone.Router
import javax.inject.Inject


@InjectViewState
class CatalogPresenter @Inject constructor(
    private val router: Router, private val dataManager: DataManager, val updateBasket: UpdateBasket
) : BasePresenter<CatalogView>(router) {

    lateinit var basket: Basket

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        getAllCatalog()

    }

    fun getAllCatalog() {
        //q.onNext("12223")
        dataManager.getDataCatalog()
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { viewState.showProgress(true) }
            .doAfterTerminate { viewState.showProgress(false) }
            .subscribe(
                { data ->
                    viewState.showProducts(data.products)
//                    viewState.showEvents(data.events)
                    viewState.visiblCatalog()

                },
                {
                    viewState.showError()
                }
            )
            .connect()
    }

    fun gotoProducCard() {
        router.navigateTo(Screens.ProductCard)
    }

    fun addProduct(products: Products) {
        basket.addProduct(products,1)

    }

    fun removeProduct(products: Products) {
        basket.deleteProduct(products,1)
    }


}