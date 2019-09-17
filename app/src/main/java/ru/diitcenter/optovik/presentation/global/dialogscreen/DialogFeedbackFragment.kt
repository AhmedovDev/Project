package ru.diitcenter.optovik.presentation.global.dialogscreen


import android.content.Context
import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_check_order.*
import kotlinx.android.synthetic.main.fragment_dialog_feedback.*
import kotlinx.android.synthetic.main.fragment_search.*
import ru.diitcenter.optovik.presentation.global.utils.hideKeyboard
import ru.example.optovik.R
import kotlin.text.clear


/**
 * A simple [Fragment] subclass.
 */
class DialogFeedbackFragment : BottomSheetDialogFragment() {

    lateinit var call: CallBack


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dialog_feedback, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    interface CallBack {
        fun setFeedBack(rating: Int, review: String)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        not_button.setOnClickListener { dialog.cancel() }
        yes_button.setOnClickListener {
            call.setFeedBack(simpleRatingBar.rating.toInt(),input_feedback.text.toString())
            dialog.cancel()
        }

        // показать клаву

        // скрыть клаву
        dialog_basket.setOnTouchListener { _, _ ->
            hideKeyboard()
            true
        }


    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        call = context as CallBack
    }

}
