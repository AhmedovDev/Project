package ru.diitcenter.optovik.di.screens.adresbook

import dagger.Subcomponent
import ru.diitcenter.optovik.presentation.screens.adresbook.ui.AdresbookActivity

@ru.diitcenter.optovik.di.screens.adresbook.AdresbookScope
@Subcomponent(modules = [ru.diitcenter.optovik.di.screens.adresbook.AdresbookModule::class])
interface AdresbookComponent {

    fun inject(adresbookActivity: AdresbookActivity)

    @Subcomponent.Builder
    interface Builder {
        fun build(): ru.diitcenter.optovik.di.screens.adresbook.AdresbookComponent
    }
}
