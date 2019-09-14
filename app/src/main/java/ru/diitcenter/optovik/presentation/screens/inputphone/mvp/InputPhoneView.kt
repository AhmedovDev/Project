package ru.diitcenter.optovik.presentation.screens.inputphone.mvp

import com.arellomobile.mvp.MvpView

interface InputPhoneView : MvpView{
    fun showProgress(progress: Boolean)
    fun showError()
    fun goToInputCode()
}