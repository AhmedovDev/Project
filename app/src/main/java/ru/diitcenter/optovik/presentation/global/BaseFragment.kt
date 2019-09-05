package ru.diitcenter.optovik.presentation.global

import com.arellomobile.mvp.MvpAppCompatFragment

abstract class BaseFragment : MvpAppCompatFragment() {
    abstract fun onBackPressed()
}
