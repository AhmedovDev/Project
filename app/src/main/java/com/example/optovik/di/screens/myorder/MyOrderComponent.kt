package com.example.optovik.di.screens.myorder

import com.example.optovik.presentation.screens.myorder.ui.MyOrderActivity
import dagger.Subcomponent

@MyOrederScope
@Subcomponent(modules = [MyOrderModule::class])
interface MyOrderComponent {

    fun inject(myOrderActivity: MyOrderActivity)

    @Subcomponent.Builder
    interface Builder {
        fun build(): MyOrderComponent
    }
}
