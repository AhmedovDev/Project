package com.example.optovik.presentation.screens.main.mvp

import com.arellomobile.mvp.MvpView
import com.example.optovik.data.global.User

interface MainView :MvpView {
    //fun showProgress(progress: Boolean)
   // fun showCategories(users: List<User>)
    fun showError (error : String)
   // fun showEvents(users: List<User>)

}
