package com.example.optovik.presentation.screens.productcard.mvp

import com.arellomobile.mvp.InjectViewState
import com.example.optovik.data.global.DataManager
import com.example.optovik.presentation.global.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class ProductCardPresenter @Inject constructor(private val router: Router, private val dataManager: DataManager) :
    BasePresenter<ProductCardView>(router,dataManager) {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        getAllData()
    }

    fun getAllData() {
        dataManager.getProductCard()
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