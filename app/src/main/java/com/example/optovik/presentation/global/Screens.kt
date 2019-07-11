package com.example.optovik.presentation.global

import android.support.v4.app.Fragment
import com.example.optovik.presentation.screens.editcode.ui.EditCodeFragment
import com.example.optovik.presentation.screens.editphone.ui.EditPhoneFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object Screens {

    object EditPhone : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return EditPhoneFragment()
        }
    }


    object Screens {
        object EditCode : SupportAppScreen() {
            override fun getFragment(): Fragment {
                return EditCodeFragment()
            }
        }
    }
}


