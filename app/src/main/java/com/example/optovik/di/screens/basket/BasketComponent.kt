package com.example.optovik.di.screens.basket

import com.example.optovik.presentation.screens.basket.ui.BasketActivity
import dagger.Subcomponent

@BasketScope
@Subcomponent(modules = [BasketModule::class])
interface BasketComponent {

    fun inject(basketActivity: BasketActivity)

    @Subcomponent.Builder
    interface Builder {
        fun build(): BasketComponent
    }
}
