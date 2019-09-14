package ru.diitcenter.optovik.presentation.screens.inputcode.mvp

import com.arellomobile.mvp.MvpView

interface InputCodeView : MvpView {
    fun isVisibleTimer(visible: Boolean)
    fun showTimeProgress()
    fun saveToken(token: String)
    fun showError()
    fun goToMain()

}