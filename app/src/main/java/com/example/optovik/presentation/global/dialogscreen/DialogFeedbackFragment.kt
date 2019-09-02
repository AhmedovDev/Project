package com.example.optovik.presentation.global.dialogscreen


import android.content.ContentValues.TAG
import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import com.example.optovik.presentation.global.utils.hideKeyboard
import com.example.optovik.presentation.global.utils.showKeyboard
import com.willy.ratingbar.BaseRatingBar
import kotlinx.android.synthetic.main.fragment_dialog_feedback.*
import kotlinx.android.synthetic.main.fragment_input_phone2.*


/**
 * A simple [Fragment] subclass.
 */
class DialogFeedbackFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(com.example.optovik.R.layout.fragment_dialog_feedback, container, false)
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
//        simpleRatingBar.setEmptyDrawableRes(com.example.optovik.R.drawable.star_active)
//        simpleRatingBar.setFilledDrawableRes(com.example.optovik.R.drawable.star_passive)


    }

    // скрыть клаву


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // показать клаву

        // скрыть клаву
        dialog_basket.setOnTouchListener { v, _ ->
            hideKeyboard(context!!, v)
            true
        }


    }


}
