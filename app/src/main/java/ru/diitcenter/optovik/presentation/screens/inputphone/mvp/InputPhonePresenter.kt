package ru.diitcenter.optovik.presentation.screens.inputphone.mvp

import com.arellomobile.mvp.InjectViewState
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class InputPhonePresenter @Inject constructor(private val router: Router,private val dataManager: ru.diitcenter.optovik.data.global.DataManager) : ru.diitcenter.optovik.presentation.global.BasePresenter<InputPhoneView>(router,dataManager) {
   fun gotoInputCode (phone: String) {
      router.replaceScreen(ru.diitcenter.optovik.presentation.global.Screens.InputCode(phone))
   }
}