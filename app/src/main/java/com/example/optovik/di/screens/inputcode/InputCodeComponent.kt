package com.example.optovik.di.screens.inputcode


import com.example.optovik.presentation.screens.inputcode.ui.InputCodeFragment
import dagger.Subcomponent

@InputCodeScope
@Subcomponent(modules = [InputCodeModule::class])
interface InputCodeComponent {

    fun inject(inputCodeFragment: InputCodeFragment)

    @Subcomponent.Builder
    interface Builder {
        fun build(): InputCodeComponent
    }
}
