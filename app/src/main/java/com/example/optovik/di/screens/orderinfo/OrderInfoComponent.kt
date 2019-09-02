package com.example.optovik.di.screens.orderinfo

import com.example.optovik.presentation.screens.orderinfo.ui.OrderInfoActivity
import dagger.Subcomponent

@OrderInfoScope
@Subcomponent(modules = [OrderInfoModule::class])
interface OrderInfoComponent {

    fun inject(orderInfoActivity: OrderInfoActivity)

    @Subcomponent.Builder
    interface Builder {
        fun build(): OrderInfoComponent
    }
}
