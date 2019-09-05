package ru.diitcenter.optovik.di.screens.notification

import dagger.Subcomponent
import ru.diitcenter.optovik.presentation.screens.notofication.ui.NotificationActivity

@ru.diitcenter.optovik.di.screens.notification.NotificationScope
@Subcomponent(modules = [ru.diitcenter.optovik.di.screens.notification.NotificationModule::class])
interface NotificationComponent {

    fun inject(notificationActivity: NotificationActivity)

    @Subcomponent.Builder
    interface Builder {
        fun build(): ru.diitcenter.optovik.di.screens.notification.NotificationComponent
    }
}
