package ru.diitcenter.optovik.di.screens.orderinfo

import dagger.Subcomponent
import ru.diitcenter.optovik.presentation.screens.orderinfo.ui.OrderInfoActivity

@ru.diitcenter.optovik.di.screens.orderinfo.OrderInfoScope
@Subcomponent(modules = [ru.diitcenter.optovik.di.screens.orderinfo.OrderInfoModule::class])
interface OrderInfoComponent {

    fun inject(orderInfoActivity: OrderInfoActivity)

    @Subcomponent.Builder
    interface Builder {
        fun build(): ru.diitcenter.optovik.di.screens.orderinfo.OrderInfoComponent
    }
}
