package ru.diitcenter.optovik.di.screens.basket

import dagger.Subcomponent
import ru.diitcenter.optovik.presentation.screens.basket.ui.BasketActivity

@ru.diitcenter.optovik.di.screens.basket.BasketScope
@Subcomponent(modules = [ru.diitcenter.optovik.di.screens.basket.BasketModule::class])
interface BasketComponent {

    fun inject(basketActivity: BasketActivity)

    @Subcomponent.Builder
    interface Builder {
        fun build(): ru.diitcenter.optovik.di.screens.basket.BasketComponent
    }
}
