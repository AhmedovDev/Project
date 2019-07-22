package com.example.optovik.presentation.screens.inputcode.ui


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.optovik.App

import com.example.optovik.R
import com.example.optovik.presentation.global.BaseFragment
import com.example.optovik.presentation.global.utils.hideKeyboard
import com.example.optovik.presentation.global.utils.withArgs
import com.example.optovik.presentation.screens.inputcode.mvp.InputCodePresenter
import com.example.optovik.presentation.screens.inputcode.mvp.InputCodeView
import kotlinx.android.synthetic.main.fragment_input_code.*
import kotlinx.android.synthetic.main.toolbar_autorization_with_arrow.*
import javax.inject.Inject
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import com.example.optovik.presentation.screens.main.ui.MainActivity


class InputCodeFragment : BaseFragment(), InputCodeView {

    override fun isVisibleTimer(visible: Boolean) {
        if (visible) {
            timer.visibility = View.VISIBLE
            getCode.visibility = View.GONE
        } else {
            timer.visibility = View.INVISIBLE
            getCode.visibility = View.VISIBLE
        }
    }

    override fun showTimeProgress(progress: Int) {
        Log.e("dfgfddsfgf", "fdgdfgdgf")
        val progressSeconds = resources.getQuantityString(R.plurals.number_of_seconds, progress, progress)
        val progressText = getString(R.string.confirmation_timer, progressSeconds)
        timer.text = progressText
    }


    @Inject
    @InjectPresenter
    lateinit var presenter: InputCodePresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    private var phone = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_input_code, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.inputCodeComponentBuilder()
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
        arguments?.run {
            phone = getString(PHONE, "")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        phoneOnEditCode.text = phone
        getCode.setOnClickListener {
            presenter.retrySendCode()
            exampleCodeCheck()
            getCode.setOnTouchListener { v, _ ->
                hideKeyboard(context!!, v)
                true
            }
        }

        back.setOnClickListener { presenter.back() }

        // Постоянная проверка поля для ввода кода на соответствие

        PinView_inputCodeFragment_code.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                exampleCodeCheck()
            }

        })
    }


    override fun onBackPressed() = presenter.back()

    companion object {

        fun newInstance(phone: String) = InputCodeFragment().withArgs {
            putString(PHONE, phone)
        }
        private const val PHONE = "phone"
    }

// todo временная проверка кода
    private fun exampleCodeCheck() {
        var code: String = "1111"
        var getCode: String = PinView_inputCodeFragment_code.text.toString()
        if (code == getCode) {
            startActivity(Intent(activity, MainActivity::class.java))
        }
    }
}
