package ru.diitcenter.optovik.di.screens.main

import dagger.Subcomponent
import ru.diitcenter.optovik.presentation.screens.main.ui.MainActivity


@ru.diitcenter.optovik.di.screens.main.MainScope
@Subcomponent(modules = [ru.diitcenter.optovik.di.screens.main.MainModule::class])
interface MainComponent {

    fun inject(mainActivity: MainActivity)

    @Subcomponent.Builder
    interface Builder {
        fun build(): ru.diitcenter.optovik.di.screens.main.MainComponent
    }
}
