package com.example.optovik.presentation.screens.catalog.mvp

import com.arellomobile.mvp.InjectViewState
import com.example.optovik.data.basketholder.BasketHolder
import com.example.optovik.data.basketholder.BasketListener
import com.example.optovik.data.global.DataManager
import com.example.optovik.data.global.models.Catalog
import com.example.optovik.data.global.models.Product
import com.example.optovik.presentation.global.BasePresenter
import com.example.optovik.presentation.global.Screens
import com.example.optovik.presentation.global.utils.UpdateBasket
import io.reactivex.android.schedulers.AndroidSchedulers
import ru.terrakok.cicerone.Router
import javax.inject.Inject


@InjectViewState
class CatalogPresenter @Inject constructor(
    private val router: Router,
    private val dataManager: DataManager,
    val updateBasket: UpdateBasket,
    private val basketHolder: BasketHolder
) : BasePresenter<CatalogView>(router, dataManager) , BasketListener {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        getAllCatalog()
        basketHolder.subscribe(this)
    }

    override fun onUpdateBasketItems(items: MutableList<BasketHolder.Item>) {
       viewState.adapterUpdate()
        viewState.updateBasketButton()
        //viewState.showProducts(basketHolder.items.map { Product(it.product.image,it.product.id,it.product.name,it.product.price,it.product.count,it.product.isEstimatedPrice,it.product.presence,it.product.quantity) })
    }


    fun getAllCatalog() {
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

    fun gotoProducCard(product: Product) {
        router.navigateTo(Screens.ProductCard(product))
    }

    fun searchCatalogClick() {
        router.navigateTo(Screens.Search)
    }
}