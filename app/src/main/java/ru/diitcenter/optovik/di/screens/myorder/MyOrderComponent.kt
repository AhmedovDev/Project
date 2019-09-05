package ru.diitcenter.optovik.di.screens.myorder

import dagger.Subcomponent
import ru.diitcenter.optovik.presentation.screens.myorder.ui.MyOrderActivity

@ru.diitcenter.optovik.di.screens.myorder.MyOrederScope
@Subcomponent(modules = [ru.diitcenter.optovik.di.screens.myorder.MyOrderModule::class])
interface MyOrderComponent {

    fun inject(myOrderActivity: MyOrderActivity)

    @Subcomponent.Builder
    interface Builder {
        fun build(): ru.diitcenter.optovik.di.screens.myorder.MyOrderComponent
    }
}
