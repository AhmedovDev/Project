package ru.diitcenter.optovik.presentation.screens.search.mvp

import com.arellomobile.mvp.InjectViewState
import io.reactivex.android.schedulers.AndroidSchedulers
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class SearchPresenter @Inject constructor(
    private val router: Router,
    private val dataManager: ru.diitcenter.optovik.data.global.DataManager,
    private val basketHolder: ru.diitcenter.optovik.data.basketholder.BasketHolder

) :
    ru.diitcenter.optovik.presentation.global.BasePresenter<SearchView>(router, dataManager),
    ru.diitcenter.optovik.data.basketholder.BasketListener {


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        basketHolder.subscribe(this)
    }

    fun goToProductCard(product: ru.diitcenter.optovik.data.global.models.Product) {
        router.navigateTo(ru.diitcenter.optovik.presentation.global.Screens.ProductCard(product))
    }

    override fun onUpdateBasketItems(items: MutableList<ru.diitcenter.optovik.data.basketholder.BasketHolder.Item>) {
        viewState.updateBasketButtonSearch()
        viewState.emptyBasketCheck()
    }

    var str = ""

    fun search(searchWord: String) {
        str = searchWord
        dataManager.searchProduct(searchWord.toString())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { viewState.showProgress(true) }
            .doAfterTerminate { viewState.showProgress(false) }
            .subscribe(
                { data ->
                    data.searchProducts?.let {
                        viewState.showFoundProducts(data.searchProducts)
                    }
                },
                {
                    viewState.showError()
                }
            )
            .connect()
    }

    fun getLastSearch() = str


    fun back() {
        router.exit()
    }
}

