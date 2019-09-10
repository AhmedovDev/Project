package ru.diitcenter.optovik.presentation.screens.productcard.mvp

import com.arellomobile.mvp.InjectViewState
import io.reactivex.android.schedulers.AndroidSchedulers
import ru.diitcenter.optovik.data.basketholder.BasketHolder
import ru.diitcenter.optovik.data.basketholder.BasketListener
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class ProductCardPresenter @Inject constructor(private val router: Router, private val dataManager: ru.diitcenter.optovik.data.global.DataManager, private val basketHolder: BasketHolder) :
    ru.diitcenter.optovik.presentation.global.BasePresenter<ProductCardView>(router,dataManager) , BasketListener{

    override fun onUpdateBasketItems(items: MutableList<BasketHolder.Item>) {
viewState.emptyBasketCheck()    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        basketHolder.subscribe(this)

    }

    fun getAllData(id: Int) {
        dataManager.getProductCard(id)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { viewState.showProgress(true) }
            .doAfterTerminate { viewState.showProgress(false) }
            .subscribe(
                { prpductCard ->
                    viewState.showProductCardInformation(prpductCard)
                    viewState.showProductCardImages(prpductCard)
                    viewState.visiblProductCard()

                },
                {
                    viewState.showError()
                }
            )
            .connect()
    }

    fun gotoback () {
        router.exit()
    }

}