package com.example.optovik.di.screens.inputphone

import com.example.optovik.presentation.screens.inputphone.ui.InputPhoneFragment
import dagger.Subcomponent

@InputPhoneScope
@Subcomponent(modules = [InputPhoneModule::class])
interface InputPhoneComponent {

    fun inject(inputPhoneFragment: InputPhoneFragment)

    @Subcomponent.Builder
    interface Builder {
        fun build(): InputPhoneComponent
    }
}
