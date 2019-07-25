package com.example.optovik.presentation.screens.catalog.mvp

import com.arellomobile.mvp.InjectViewState
import com.example.optovik.data.global.DataManager
import com.example.optovik.presentation.global.BasePresenter
import com.example.optovik.presentation.global.Screens
import com.example.optovik.presentation.global.utils.NetworkChecking
import io.reactivex.android.schedulers.AndroidSchedulers
import ru.terrakok.cicerone.Router
import javax.inject.Inject


@InjectViewState
class CatalogPresenter @Inject constructor(
    private val router: Router, private val dataManager: DataManager, val networkChecking: NetworkChecking
) : BasePresenter<CatalogView>(router) {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        getAllCatalog()
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


}