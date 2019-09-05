package ru.diitcenter.optovik.presentation.screens.adresbook.mvp

import com.arellomobile.mvp.InjectViewState
import io.reactivex.android.schedulers.AndroidSchedulers
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class AdresbookPresenter @Inject constructor(
    private val router: Router,
    private val dataManager: ru.diitcenter.optovik.data.global.DataManager,
    private val basketHolder: ru.diitcenter.optovik.data.basketholder.BasketHolder
) : ru.diitcenter.optovik.presentation.global.BasePresenter<AdresbookView>(router, dataManager)
{
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        getAllLocations()
    }

    fun getAllLocations() {
        dataManager.getLocation()
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { viewState.showProgress(true) }
            .doAfterTerminate { viewState.showProgress(false) }
            .subscribe(
                { data ->
                    data
                    viewState.showLocations(data)
                },
                {
                    viewState.showError()
                }
            )
            .connect()
    }

}