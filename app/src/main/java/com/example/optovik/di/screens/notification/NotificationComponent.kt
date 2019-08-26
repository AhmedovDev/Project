package com.example.optovik.di.screens.notification

import com.example.optovik.presentation.screens.notofication.ui.NotificationActivity
import dagger.Subcomponent

@NotificationScope
@Subcomponent(modules = [NotificationModule::class])
interface NotificationComponent {

    fun inject(notificationActivity: NotificationActivity)

    @Subcomponent.Builder
    interface Builder {
        fun build(): NotificationComponent
    }
}
