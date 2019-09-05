package ru.diitcenter.optovik.di.screens.catalog

import dagger.Subcomponent
import ru.diitcenter.optovik.presentation.screens.catalog.ui.CatalogActivity

@ru.diitcenter.optovik.di.screens.catalog.CatalogScope
@Subcomponent(modules = [ru.diitcenter.optovik.di.screens.catalog.CatalogModule::class])
interface CatalogComponent {

    fun inject(catalogActivity: CatalogActivity)

    @Subcomponent.Builder
    interface Builder {
        fun build(): ru.diitcenter.optovik.di.screens.catalog.CatalogComponent
    }
}
