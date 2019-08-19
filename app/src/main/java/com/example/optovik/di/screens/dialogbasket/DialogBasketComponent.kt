package com.example.optovik.di.screens.dialogbasket

import com.example.optovik.presentation.global.dialogscreen.DialogBasketFragment
import dagger.Subcomponent

@DialogBasketScope
@Subcomponent(modules = [DialogBasketModule::class])
interface DialogBasketComponent {

    fun inject(dialogBasketFragment: DialogBasketFragment)

    @Subcomponent.Builder
    interface Builder {
        fun build(): DialogBasketComponent
    }
}
