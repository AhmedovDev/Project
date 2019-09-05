package ru.diitcenter.optovik.presentation.global.dialogscreen


import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_dialog_feedback.*
import kotlinx.android.synthetic.main.fragment_search.*
import ru.diitcenter.optovik.presentation.global.utils.hideKeyboard
import ru.example.optovik.R
import kotlin.text.clear


/**
 * A simple [Fragment] subclass.
 */
class DialogFeedbackFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dialog_feedback, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



//        simpleRatingBar.numStars = 5
//        simpleRatingBar.setMinimumStars(1f)
//        simpleRatingBar.rating = 3f
//        simpleRatingBar.starPadding = 10
//        simpleRatingBar.stepSize = 0.5f
//        simpleRatingBar.setIsIndicator(false)
//        simpleRatingBar.setClickable(true)
//        simpleRatingBar.setScrollable(true)
//        simpleRatingBar.setClearRatingEnabled(true)
//        simpleRatingBar.setEmptyDrawableRes(com.diitcenter.optovik.R.drawable.star_active)
//        simpleRatingBar.setFilledDrawableRes(com.diitcenter.optovik.R.drawable.star_passive)


    }

    // скрыть клаву


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

not_button.setOnClickListener { dialog.cancel() }
        yes_button.setOnClickListener { dialog.cancel() }

        // показать клаву

        // скрыть клаву
        dialog_basket.setOnTouchListener { v, _ ->
            hideKeyboard(context!!, v)
            true
        }


    }


}
