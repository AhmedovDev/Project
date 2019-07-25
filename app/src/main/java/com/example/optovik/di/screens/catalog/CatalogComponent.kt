package com.example.optovik.di.screens.catalog

import com.example.optovik.presentation.screens.catalog.ui.CatalogActivity
import dagger.Subcomponent

@CatalogScope
@Subcomponent(modules = [CatalogModule::class])
interface CatalogComponent {

    fun inject(catalogActivity: CatalogActivity)

    @Subcomponent.Builder
    interface Builder {
        fun build(): CatalogComponent
    }
}
