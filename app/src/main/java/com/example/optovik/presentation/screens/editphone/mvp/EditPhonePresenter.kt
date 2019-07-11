package com.example.optovik.presentation.screens.editphone.mvp

import com.arellomobile.mvp.InjectViewState
import com.example.optovik.presentation.global.BasePresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class EditPhonePresenter @Inject constructor(private val router: Router) : BasePresenter<EditPhoneView>(router) {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
    }
}