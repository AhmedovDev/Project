package ru.diitcenter.optovik.presentation.screens.inputphone.ui


import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.TransitionDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_input_phone2.*
import ru.diitcenter.optovik.data.prefs.PrefsHelper
import ru.diitcenter.optovik.presentation.global.utils.hideKeyboard
import ru.diitcenter.optovik.presentation.global.utils.showKeyboard
import ru.diitcenter.optovik.presentation.screens.inputphone.mvp.InputPhonePresenter
import ru.diitcenter.optovik.presentation.screens.inputphone.mvp.InputPhoneView
import ru.diitcenter.optovik.presentation.screens.splash.ui.SplashActivity
import ru.example.optovik.R
import javax.inject.Inject

class InputPhoneFragment : ru.diitcenter.optovik.presentation.global.BaseFragment(),
    InputPhoneView {

    override fun showProblem() {
        Toast.makeText(context,"Ваш номер не зарегистрирован", Toast.LENGTH_SHORT).show()
    }

    override fun showError() {
        inputphone_container.visibility = View.VISIBLE
        message.visibility = View.GONE
    }

    override fun showProgress(progress: Boolean) {
    }

    @Inject
    lateinit var prefsHelper: PrefsHelper

    @Inject
    @InjectPresenter
    lateinit var presenter: InputPhonePresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        ru.diitcenter.optovik.App.appComponent.inputPhoneComponentBuilder()
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

        // показать клаву
        inputphone.setOnTouchListener { _, _ ->
            showKeyboard(context!!)
            true
        }
        // скрыть клаву
        inputphone.setOnTouchListener { _, _ ->
            hideKeyboard()
            true
        }

        phone.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                hideKeyboard()
            }
            false
        }

        // проверка поля на заполненность для активации кнопки
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
// todo уточнить что делать при ошибке
//        update_inputphone.setOnClickListener {   startActivity(Intent(activity, SplashActivity::class.java))
//        }

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

    override fun goToInputCode() {
        prefsHelper.savePhone(phone.text.toString())
        presenter.gotoInputCode(phone.text.toString())
        inputphone_container.visibility = View.GONE
        message.visibility = View.VISIBLE
    }

    private fun isDisabledButton() {
        (buttonNext.background as TransitionDrawable).reverseTransition(100)
        buttonNext.setTextColor(resources.getColor(R.color.colorTextHint))
        buttonNext.isEnabled = false
    }


    fun onButtonClicked() {
        var res = phone.text.toString().replace("""\D+""".toRegex(), "")
        presenter.getCode(res)

    }


    override fun onBackPressed() = presenter.onBackPressed()
}


