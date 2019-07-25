package com.example.optovik.presentation.screens.main.mvp

import com.arellomobile.mvp.MvpView
import com.example.optovik.data.global.models.Category
import com.example.optovik.data.global.models.DataModel
import com.example.optovik.data.global.models.Event

interface MainView : MvpView {
    fun showProgress(progress: Boolean)
    fun showCategories(users: List<Category>)
    fun showError()
    fun showEvents(banner: List<Event>)
    fun visiblMain()
    fun categoryesClick()

}
