package com.example.optovik.di.screens.search

import com.example.optovik.presentation.screens.search.ui.SearchFragment
import dagger.Subcomponent

@SearchScope
@Subcomponent(modules = [SearchModule::class])
interface SearchComponent {

    fun inject(searchFragment: SearchFragment)

    @Subcomponent.Builder
    interface Builder {
        fun build(): SearchComponent
    }
}
