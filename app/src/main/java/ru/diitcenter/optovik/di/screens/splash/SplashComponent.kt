package ru.diitcenter.optovik.di.screens.splash

import dagger.Subcomponent
import ru.diitcenter.optovik.presentation.screens.splash.ui.SplashActivity

@ru.diitcenter.optovik.di.screens.splash.SplashScope
@Subcomponent(modules = [ru.diitcenter.optovik.di.screens.splash.SplashModule::class])
interface SplashComponent {

    fun inject(splashActivity: SplashActivity)

    @Subcomponent.Builder
    interface Builder {
        fun build(): ru.diitcenter.optovik.di.screens.splash.SplashComponent
    }
}
