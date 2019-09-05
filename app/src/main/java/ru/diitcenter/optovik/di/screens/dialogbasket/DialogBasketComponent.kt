package ru.diitcenter.optovik.di.screens.dialogbasket

import dagger.Subcomponent

@ru.diitcenter.optovik.di.screens.dialogbasket.DialogBasketScope
@Subcomponent(modules = [ru.diitcenter.optovik.di.screens.dialogbasket.DialogBasketModule::class])
interface DialogBasketComponent {

    fun inject(dialogBasketFragment: ru.diitcenter.optovik.presentation.global.dialogscreen.DialogBasketFragment)

    @Subcomponent.Builder
    interface Builder {
        fun build(): ru.diitcenter.optovik.di.screens.dialogbasket.DialogBasketComponent
    }
}
