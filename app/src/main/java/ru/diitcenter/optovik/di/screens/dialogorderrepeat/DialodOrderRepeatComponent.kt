package ru.diitcenter.optovik.di.screens.dialogorderrepeat

import dagger.Subcomponent
import ru.diitcenter.optovik.presentation.global.dialogscreen.DialogOrderRepeatFragment

@DialogOrderRepeatScope
@Subcomponent(modules = [DialogOrderRepeatModule::class])
interface DialodOrderRepeatComponent {

    fun inject(dialogOrderRepeatFragment: DialogOrderRepeatFragment)

    @Subcomponent.Builder
    interface Builder {
        fun build(): DialodOrderRepeatComponent
    }
}
