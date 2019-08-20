package com.example.optovik.di.screens.adresbook

import com.example.optovik.presentation.screens.adresbook.ui.AdresbookActivity
import dagger.Subcomponent

@AdresbookScope
@Subcomponent(modules = [AdresbookModule::class])
interface AdresbookComponent {

    fun inject(adresbookActivity: AdresbookActivity)

    @Subcomponent.Builder
    interface Builder {
        fun build(): AdresbookComponent
    }
}
