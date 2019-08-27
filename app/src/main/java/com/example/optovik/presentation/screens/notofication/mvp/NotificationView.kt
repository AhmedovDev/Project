package com.example.optovik.presentation.screens.notofication.mvp

import com.arellomobile.mvp.MvpView
import com.example.optovik.data.global.models.MyOrder
import com.example.optovik.data.global.models.Notification

interface NotificationView : MvpView {
    fun showProgress(progress: Boolean)
    fun showNotification(notification: List<Notification>)
    fun showError()
}