package ru.diitcenter.optovik.presentation.screens.notofication.mvp

import com.arellomobile.mvp.MvpView

interface NotificationView : MvpView {
    fun showProgress(progress: Boolean)
    fun showNotification(notification: List<ru.diitcenter.optovik.data.global.models.Notification>)
    fun showError()
}