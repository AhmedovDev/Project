package ru.diitcenter.optovik.presentation.screens.autorization.mvp

import com.arellomobile.mvp.InjectViewState
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class AutorizationPresenter @Inject constructor(private val router: Router,private val dataManager: ru.diitcenter.optovik.data.global.DataManager) : ru.diitcenter.optovik.presentation.global.BasePresenter<AutorizationView>(router,dataManager) {

    override fun onFirstViewAttach() {
        router.newRootChain(ru.diitcenter.optovik.presentation.global.Screens.InputPhone)
    }
}