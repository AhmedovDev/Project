package com.example.optovik.presentation.global

import android.support.v4.app.Fragment
import com.example.optovik.data.global.models.Product
import com.example.optovik.presentation.screens.inputcode.ui.InputCodeFragment
import com.example.optovik.presentation.screens.inputphone.ui.InputPhoneFragment
import com.example.optovik.presentation.screens.productcard.ui.ProductCargFragment
import com.example.optovik.presentation.screens.search.ui.SearchFragment
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

    class ProductCard (private val product: Product) : SupportAppScreen() {
        override fun getFragment()= ProductCargFragment.newInstance(product)
        }
    }





