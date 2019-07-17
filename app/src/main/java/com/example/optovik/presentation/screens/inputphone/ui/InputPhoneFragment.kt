package com.example.optovik.presentation.screens.inputphone.ui


import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.TransitionDrawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.optovik.App
import com.example.optovik.R
import com.example.optovik.presentation.global.BaseFragment
import com.example.optovik.presentation.global.utils.hideKeyboard
import com.example.optovik.presentation.global.utils.showKeyboard
import com.example.optovik.presentation.screens.inputphone.mvp.InputPhonePresenter
import com.example.optovik.presentation.screens.inputphone.mvp.InputPhoneView
import kotlinx.android.synthetic.main.fragment_input_code.*
import kotlinx.android.synthetic.main.fragment_input_phone2.*
import javax.inject.Inject

class InputPhoneFragment : BaseFragment(), InputPhoneView {


    @Inject
    @InjectPresenter
    lateinit var presenter: InputPhonePresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.inputPhoneComponentBuilder()
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)


    }

    var nextButtonTransitionIsStarted = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_input_phone2, container, false)

    }

    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        inputphone.setOnTouchListener { v, _ ->
            showKeyboard(context!!)
            true
        }

        inputphone.setOnTouchListener { v, _ ->
            hideKeyboard(context!!, v)
            true
        }

        phone.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                hideKeyboard(context!!, view)
            }
            false
        }

        phone.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (phone.rawText.length > 9) {
                    if (!nextButtonTransitionIsStarted) {
                        isEnabledButton()
                        nextButtonTransitionIsStarted = true
                    }
                } else {
                    if (nextButtonTransitionIsStarted) {
                        isDisabledButton()
                        nextButtonTransitionIsStarted = false
                    }
                }
            }
        })

    }

    private fun isEnabledButton() {
        (buttonNext.background as TransitionDrawable).startTransition(100)
        buttonNext.setTextColor(Color.WHITE)
        buttonNext.isEnabled = true
        buttonNext.setOnClickListener {
            onButtonClicked()
        }
        phone.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                onButtonClicked()
            }
            false
        }

    }

    private fun isDisabledButton() {
        (buttonNext.background as TransitionDrawable).reverseTransition(100)
        buttonNext.setTextColor(resources.getColor(R.color.colorTextHint))
        buttonNext.isEnabled = false
    }


    fun onButtonClicked() {
        presenter.gotoInputCode(phone.text.toString())
    }


    override fun onBackPressed() = presenter.onBackPressed()
}

