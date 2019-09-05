package ru.diitcenter.optovik.di.screens.search

import dagger.Subcomponent
import ru.diitcenter.optovik.presentation.screens.search.ui.SearchFragment

@ru.diitcenter.optovik.di.screens.search.SearchScope
@Subcomponent(modules = [ru.diitcenter.optovik.di.screens.search.SearchModule::class])
interface SearchComponent {

    fun inject(searchFragment: SearchFragment)

    @Subcomponent.Builder
    interface Builder {
        fun build(): ru.diitcenter.optovik.di.screens.search.SearchComponent
    }
}
