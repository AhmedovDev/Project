package com.example.optovik.presentation.screens.autorization.mvp

import com.arellomobile.mvp.InjectViewState
import com.example.optovik.presentation.global.BasePresenter
import com.example.optovik.presentation.global.Screens
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class AutorizationPresenter @Inject constructor(private val router: Router) : BasePresenter<AutorizationView>(router) {

    override fun onFirstViewAttach() {
        router.newRootChain(Screens.InputPhone)
    }
}