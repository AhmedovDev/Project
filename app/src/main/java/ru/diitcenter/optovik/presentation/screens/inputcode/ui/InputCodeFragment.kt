package ru.diitcenter.optovik.presentation.screens.inputcode.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_input_code.*
import kotlinx.android.synthetic.main.toolbar_autorization_with_arrow.*
import javax.inject.Inject
import android.content.Intent
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import ru.diitcenter.optovik.data.prefs.PrefsHelper
import ru.diitcenter.optovik.presentation.global.utils.hideKeyboard
import ru.diitcenter.optovik.presentation.global.utils.withArgs
import ru.diitcenter.optovik.presentation.screens.inputcode.mvp.InputCodePresenter
import ru.diitcenter.optovik.presentation.screens.inputcode.mvp.InputCodeView
import ru.diitcenter.optovik.presentation.screens.main.ui.MainActivity
import ru.example.optovik.R


class InputCodeFragment : ru.diitcenter.optovik.presentation.global.BaseFragment(), InputCodeView {

    @Inject
    lateinit var prefsHelper: PrefsHelper

    @Inject
    @InjectPresenter
    lateinit var presenter: InputCodePresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    private val countDownTimer = object : CountDownTimer(60000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            var time = millisUntilFinished / 1000
            val progressSeconds =
                resources.getQuantityString(R.plurals.number_of_seconds, time.toInt(), time.toInt())
            val progressText = getString(R.string.confirmation_timer, progressSeconds)
            timer.text = progressText
        }

        override fun onFinish() {
            isVisibleTimer(false)
            getCode.setOnClickListener { presenter.retrySendCode() }
        }
    }

    private var phone = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        ru.diitcenter.optovik.App.appComponent.inputCodeComponentBuilder()
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
        arguments?.run {
            phone = getString(PHONE, "").replace("""\D+""".toRegex(), "")
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_input_code, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        phoneOnEditCode.text = prefsHelper.getPhone()

        getCode.setOnTouchListener { _, _ ->
            hideKeyboard()
            true
        }

        getCode.setOnClickListener { presenter.getCode(phone) }

        back.setOnClickListener { presenter.back() }


        // Постоянная проверка поля для ввода кода на соответствие

        PinView_inputCodeFragment_code.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {


            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (PinView_inputCodeFragment_code.text.toString().length == 4)
                    presenter.getToken(phone, PinView_inputCodeFragment_code.text.toString())
            }

        })
    }

    override fun goToMain() {
        startActivity(Intent(activity, MainActivity::class.java))
    }


    override fun saveToken(token: String) {
        prefsHelper.saveToken(token)
    }

    override fun showError() {

        Toast.makeText(context, "Код неверный, \n повторите попытку", Toast.LENGTH_SHORT).show()
    }


    override fun isVisibleTimer(visible: Boolean) {
        if (visible) {
            timer.visibility = View.VISIBLE
            getCode.visibility = View.GONE
        } else {
            timer.visibility = View.INVISIBLE
            getCode.visibility = View.VISIBLE
        }
    }

    // таймер
    override fun showTimeProgress() {
        countDownTimer.start()
    }

    override fun onStop() {
        super.onStop()
        countDownTimer.cancel()
    }


    override fun onBackPressed() = presenter.back()

    companion object {

        fun newInstance(phone: String) = InputCodeFragment().withArgs {
            putString(PHONE, phone)
        }

        private const val PHONE = "phone"
    }
}
