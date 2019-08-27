package com.example.optovik.di.screens.checkorder

import com.example.optovik.presentation.screens.checkorder.ui.CheckOrderActivity
import dagger.Subcomponent

@CheckOrderScope
@Subcomponent(modules = [CheckOrderModule::class])
interface CheckOrderComponent {

    fun inject(checkOrderActivity: CheckOrderActivity)

    @Subcomponent.Builder
    interface Builder {
        fun build(): CheckOrderComponent
    }
}
