package com.example.optovik.presentation.screens.inputphone.mvp

import com.arellomobile.mvp.InjectViewState
import com.example.optovik.data.global.DataManager
import com.example.optovik.presentation.global.BasePresenter
import com.example.optovik.presentation.global.Screens
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class InputPhonePresenter @Inject constructor(private val router: Router,private val dataManager: DataManager) : BasePresenter<InputPhoneView>(router,dataManager) {
   fun gotoInputCode (phone: String) {
      router.replaceScreen(Screens.InputCode(phone))
   }
}