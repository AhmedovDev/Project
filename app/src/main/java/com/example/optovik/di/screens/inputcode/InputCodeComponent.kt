package com.example.optovik.di.screens.inputcode

import com.example.optovik.di.screens.inputphone.InputPhoneModule
import com.example.optovik.di.screens.inputphone.InputPhoneScope
import com.example.optovik.presentation.screens.inputcode.ui.InputCodeFragment
import com.example.optovik.presentation.screens.inputphone.ui.InputPhoneFragment
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
