package ru.diitcenter.optovik.presentation.global

import android.support.v4.app.Fragment
import ru.diitcenter.optovik.presentation.screens.inputcode.ui.InputCodeFragment
import ru.diitcenter.optovik.presentation.screens.inputphone.ui.InputPhoneFragment
import ru.diitcenter.optovik.presentation.screens.productcard.ui.ProductCargFragment
import ru.diitcenter.optovik.presentation.screens.search.ui.SearchFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object Screens {

    object InputPhone : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return InputPhoneFragment()
        }
    }

    object Search : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return SearchFragment()
        }
    }



    class InputCode(private val phone: String) : SupportAppScreen() {
        override fun getFragment() = InputCodeFragment.newInstance(phone)
    }

    class ProductCard (private val product: ru.diitcenter.optovik.data.global.models.Product) : SupportAppScreen() {
        override fun getFragment()= ProductCargFragment.newInstance(product)
        }
    }





