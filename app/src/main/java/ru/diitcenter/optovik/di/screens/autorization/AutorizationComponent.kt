package ru.diitcenter.optovik.di.screens.autorization

import ru.diitcenter.optovik.presentation.screens.autorization.ui.AutorizationActivity
import dagger.Subcomponent


@ru.diitcenter.optovik.di.screens.autorization.AutorizationScope
@Subcomponent(modules = [ru.diitcenter.optovik.di.screens.autorization.AutorizationModule::class])
interface AutorizationComponent {

    fun inject(autorizationActivity: AutorizationActivity)

    @Subcomponent.Builder
    interface Builder {
        fun build(): ru.diitcenter.optovik.di.screens.autorization.AutorizationComponent
    }
}
