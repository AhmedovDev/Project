package ru.diitcenter.optovik.presentation.screens.checkorder.mvp

import com.arellomobile.mvp.MvpView
import ru.diitcenter.optovik.data.global.models.OperatorPhone

interface CheckOrderView : MvpView {
    fun showError(show: Boolean)
    fun goToMain()
}