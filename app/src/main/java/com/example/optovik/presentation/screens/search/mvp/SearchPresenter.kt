package com.example.optovik.presentation.screens.search.mvp

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.example.optovik.data.global.DataManager
import com.example.optovik.data.global.models.Product
import com.example.optovik.presentation.global.BasePresenter
import com.example.optovik.presentation.global.Screens
import io.reactivex.android.schedulers.AndroidSchedulers
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class SearchPresenter @Inject constructor(
    private val router: Router,
    private val dataManager: DataManager
) :
    BasePresenter<SearchView>(router, dataManager) {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
    }

    fun goToProductCard(product: Product) {
        router.navigateTo(Screens.ProductCard(product))
    }

    fun search(searchWord: String) {
        var products: MutableList<Product> = ArrayList()
        var firstproducts: List<Product> = ArrayList()
        var emptyArray: List<Product> = ArrayList()
        dataManager.getDataCatalog()
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { viewState.showProgress(true) }
            .doAfterTerminate { viewState.showProgress(false) }
            .subscribe(
                { data ->
                    viewState.visiblSearchList()
                    firstproducts = data.products
                    firstproducts.forEach { item ->
                        if (item.name.contains(searchWord)) products.add(item)
                    }
                    if (products.size != 0 && searchWord != "")
                        viewState.showFoundProducts(products)
                    else
                        viewState.showFoundProducts(emptyArray)

                },
                {
                    viewState.showError()
                }
            )
            .connect()


    }

    fun back() {
        router.exit()
    }
}

