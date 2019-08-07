package com.example.optovik.presentation.screens.basket.mvp

import com.arellomobile.mvp.InjectViewState
import com.example.optovik.data.basketholder.BasketHolder
import com.example.optovik.data.global.DataManager
import com.example.optovik.presentation.global.BasePresenter
import com.example.optovik.presentation.global.Screens
import com.example.optovik.presentation.global.utils.UpdateBasket
import io.reactivex.android.schedulers.AndroidSchedulers
import ru.terrakok.cicerone.Router
import javax.inject.Inject


@InjectViewState
class BasketPresenter @Inject constructor(
    private val router: Router, private val dataManager: DataManager, val updateBasket: UpdateBasket, basketHolder: BasketHolder
) : BasePresenter<BasketView>(router,dataManager) {


    override fun onFirstViewAttach() {
        getBasket()
    }

    fun getBasket(){
        dataManager.getBasket()
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { viewState.showProgress(true) }
            .doAfterTerminate { viewState.showProgress(false) }
            .subscribe(
                { data ->
                    viewState.showBasket(data)
                    viewState.visiblBasket()

                },
                {
                    viewState.showError()
                }
            )
            .connect()
    }


    fun getBasketlocal (){
        return
        dataManager.getBasket()
            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(
//                { data ->
//
//
//                },
//                {
//                }
//            )
//            .connect()
    }

    fun gotoProductCard(){
        router.navigateTo(Screens.ProductCard)
    }
}
