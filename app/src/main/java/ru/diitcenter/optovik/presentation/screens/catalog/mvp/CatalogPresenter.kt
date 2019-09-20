package ru.diitcenter.optovik.presentation.screens.catalog.mvp

import com.arellomobile.mvp.InjectViewState
import io.reactivex.android.schedulers.AndroidSchedulers
import ru.terrakok.cicerone.Router
import javax.inject.Inject


@InjectViewState
class CatalogPresenter @Inject constructor(
    private val router: Router,
    private val dataManager: ru.diitcenter.optovik.data.global.DataManager,
    private val basketHolder: ru.diitcenter.optovik.data.basketholder.BasketHolder
) : ru.diitcenter.optovik.presentation.global.BasePresenter<CatalogView>(router, dataManager) ,
    ru.diitcenter.optovik.data.basketholder.BasketListener {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
       // getAllCatalog()
        basketHolder.subscribe(this)
    }

    override fun onUpdateBasketItems(items: MutableList<ru.diitcenter.optovik.data.basketholder.BasketHolder.Item>) {
       viewState.adapterUpdate()
        viewState.updateBasketButton()
        viewState.emptyBasketCheck()
        //viewState.showProducts(basketHolder.items.map { Product(it.product.image,it.product.id,it.product.name,it.product.price,it.product.count,it.product.isEstimatedPrice,it.product.presence,it.product.quantity) })
    }


    fun getAllCatalog(id : Int) {
        dataManager.getDataCatalog(id)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { viewState.showProgress(true) }
            .doAfterTerminate { viewState.showProgress(false) }
            .subscribe(
                { data ->
                        viewState.visiblCatalog()
                        viewState.showProducts(data.products)
                        viewState.showInformation(data.information)


                },
                {
                    viewState.showError()
                }
            )
            .connect()
    }

    fun gotoProducCard(productId : Int) {
        router.navigateTo(ru.diitcenter.optovik.presentation.global.Screens.ProductCard(productId))
    }

    fun searchCatalogClick() {
        router.navigateTo(ru.diitcenter.optovik.presentation.global.Screens.Search)
    }
}