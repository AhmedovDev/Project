package com.example.optovik.presentation.screens.editphone.ui


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import br.com.sapereaude.maskedEditText.MaskedEditText
import com.example.optovik.R
import com.example.optovik.presentation.global.utils.hideKeyboard
import com.example.optovik.presentation.global.utils.showKeyboard
import kotlinx.android.synthetic.main.fragment_edit_phone2.*

class EditPhoneFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_phone2, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editphone.setOnTouchListener { v, _ ->
            showKeyboard(context!!)
            true
        }

        editphone.setOnTouchListener { v, _ ->
            hideKeyboard(context!!, v)
            true
        }

        phone.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                hideKeyboard(context!!, view)
            }
            false
        }


    }
}
