package ru.diitcenter.optovik.presentation.screens.adresbook.mvp

import com.arellomobile.mvp.MvpView

interface AdresbookView : MvpView {
    fun showProgress(progress: Boolean)
    fun showLocations(locations: List<ru.diitcenter.optovik.data.global.models.Location>)
    fun showError()
}