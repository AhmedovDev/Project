package ru.diitcenter.optovik.presentation.global.dialogscreen


import android.content.Context
import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_order_info.*
import kotlinx.android.synthetic.main.fragment_dialog_basket.*
import kotlinx.android.synthetic.main.fragment_dialog_order_repeat.*
import ru.diitcenter.optovik.App
import ru.example.optovik.R
import javax.inject.Inject


class DialogOrderRepeatFragment : BottomSheetDialogFragment() {

    lateinit var call: CallBackDialogOrderRepeat

    @Inject
    lateinit var basketHolder: ru.diitcenter.optovik.data.basketholder.BasketHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.dialogOdrerRepeatComponentBuilder()
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dialog_order_repeat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        not_order_repeat.setOnClickListener { dialog.cancel() }

        yes_order_repeat.setOnClickListener {
            dialog.cancel()
         call.replaseBasket()
        }

    }

    public interface CallBackDialogOrderRepeat {
        fun replaseBasket()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        call = context as CallBackDialogOrderRepeat

    }

}
