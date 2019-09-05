package ru.diitcenter.optovik.di.screens.inputcode


import dagger.Subcomponent
import ru.diitcenter.optovik.presentation.screens.inputcode.ui.InputCodeFragment

@ru.diitcenter.optovik.di.screens.inputcode.InputCodeScope
@Subcomponent(modules = [ru.diitcenter.optovik.di.screens.inputcode.InputCodeModule::class])
interface InputCodeComponent {

    fun inject(inputCodeFragment: InputCodeFragment)

    @Subcomponent.Builder
    interface Builder {
        fun build(): ru.diitcenter.optovik.di.screens.inputcode.InputCodeComponent
    }
}
