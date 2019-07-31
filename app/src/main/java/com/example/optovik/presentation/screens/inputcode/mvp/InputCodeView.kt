package com.example.optovik.presentation.screens.inputcode.mvp

import com.arellomobile.mvp.MvpView

interface InputCodeView : MvpView {
    fun isVisibleTimer(visible: Boolean)
    fun showTimeProgress()

}