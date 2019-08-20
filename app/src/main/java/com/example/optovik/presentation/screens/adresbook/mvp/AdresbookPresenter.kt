package com.example.optovik.presentation.screens.adresbook.mvp

import com.arellomobile.mvp.InjectViewState
import com.example.optovik.data.basketholder.BasketHolder
import com.example.optovik.data.global.DataManager
import com.example.optovik.presentation.global.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class AdresbookPresenter @Inject constructor(
    private val router: Router,
    private val dataManager: DataManager,
    private val basketHolder: BasketHolder
) : BasePresenter<AdresbookView>(router, dataManager)
{

    fun getAllLocations() {
        dataManager.getLocation()
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { viewState.showProgress(true) }
            .doAfterTerminate { viewState.showProgress(false) }
            .subscribe(
                { data ->
                    viewState.showLocations(data)
                },
                {
                    viewState.showError()
                }
            )
            .connect()
    }

}