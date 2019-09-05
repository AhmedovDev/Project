package ru.diitcenter.optovik.di.screens.inputphone

import dagger.Subcomponent
import ru.diitcenter.optovik.presentation.screens.inputphone.ui.InputPhoneFragment

@ru.diitcenter.optovik.di.screens.inputphone.InputPhoneScope
@Subcomponent(modules = [ru.diitcenter.optovik.di.screens.inputphone.InputPhoneModule::class])
interface InputPhoneComponent {

    fun inject(inputPhoneFragment: InputPhoneFragment)

    @Subcomponent.Builder
    interface Builder {
        fun build(): ru.diitcenter.optovik.di.screens.inputphone.InputPhoneComponent
    }
}
