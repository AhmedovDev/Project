package ru.diitcenter.optovik.di.screens.productcard

import dagger.Subcomponent
import ru.diitcenter.optovik.presentation.screens.productcard.ui.ProductCargFragment

@ru.diitcenter.optovik.di.screens.productcard.ProductCardScope
@Subcomponent(modules = [ru.diitcenter.optovik.di.screens.productcard.ProductCardModules::class])
interface ProductCardComponent {

    fun inject(productCardFragment: ProductCargFragment)

    @Subcomponent.Builder
    interface Builder {
        fun build(): ru.diitcenter.optovik.di.screens.productcard.ProductCardComponent
    }
}