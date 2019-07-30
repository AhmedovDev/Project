package com.example.optovik.di.screens.productcard

import com.example.optovik.presentation.screens.productcard.ui.ProductCargFragment
import dagger.Subcomponent

@ProductCardScope
@Subcomponent(modules = [ProductCardModules::class])
interface ProductCardComponent {

    fun inject(productCardFragment: ProductCargFragment)

    @Subcomponent.Builder
    interface Builder {
        fun build(): ProductCardComponent
    }
}