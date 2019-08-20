package com.example.optovik.presentation.screens.adresbook.mvp

import com.arellomobile.mvp.MvpView
import com.example.optovik.data.global.models.Location

interface AdresbookView : MvpView {
    fun showProgress(progress: Boolean)
    fun showLocations(locations: List<Location>)
    fun showError()
}