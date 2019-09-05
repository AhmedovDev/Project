package ru.diitcenter.optovik.di.screens.checkorder

import dagger.Subcomponent
import ru.diitcenter.optovik.presentation.screens.checkorder.ui.CheckOrderActivity

@ru.diitcenter.optovik.di.screens.checkorder.CheckOrderScope
@Subcomponent(modules = [ru.diitcenter.optovik.di.screens.checkorder.CheckOrderModule::class])
interface CheckOrderComponent {

    fun inject(checkOrderActivity: CheckOrderActivity)

    @Subcomponent.Builder
    interface Builder {
        fun build(): ru.diitcenter.optovik.di.screens.checkorder.CheckOrderComponent
    }
}
