package com.example.optovik.presentation.screens.inputphone.mvp

import com.arellomobile.mvp.InjectViewState
import com.example.optovik.presentation.global.BasePresenter
import com.example.optovik.presentation.global.Screens
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class InputPhonePresenter @Inject constructor(private val router: Router) : BasePresenter<InputPhoneView>(router) {
   fun gotoInputCode (phone: String) {
      router.replaceScreen(Screens.InputCode(phone))
   }
}