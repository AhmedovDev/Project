package com.example.optovik.presentation.global.dialogscreen


import android.content.Context
import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.optovik.App
import com.example.optovik.data.basketholder.BasketHolder
import kotlinx.android.synthetic.main.fragment_dialog_basket.*
import javax.inject.Inject



class DialogBasketFragment : BottomSheetDialogFragment() {

    lateinit var call: CallBack

    @Inject
    lateinit var basketHolder: BasketHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.dialogBasketComponentBuilder()
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(com.example.optovik.R.layout.fragment_dialog_basket, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        not_drop_button.setOnClickListener { dialog.cancel() }

        drop_button.setOnClickListener {
            basketHolder.items.clear()
            dialog.cancel()
            call.updateBasketView()


        }

    }

    public interface CallBack {
        fun updateBasketView()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        call = context as CallBack
        }

}
