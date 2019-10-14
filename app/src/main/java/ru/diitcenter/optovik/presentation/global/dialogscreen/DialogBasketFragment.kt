package ru.diitcenter.optovik.presentation.global.dialogscreen


import android.content.Context
import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_dialog_basket.*
import ru.example.optovik.R
import javax.inject.Inject



class DialogBasketFragment : BottomSheetDialogFragment() {

    lateinit var call: ru.diitcenter.optovik.presentation.global.dialogscreen.DialogBasketFragment.CallBack

    @Inject
    lateinit var basketHolder: ru.diitcenter.optovik.data.basketholder.BasketHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        ru.diitcenter.optovik.App.appComponent.dialogBasketComponentBuilder()
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dialog_basket, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        not_drop_button.setOnClickListener { dialog.cancel() }

        drop_button.setOnClickListener {
            dialog.cancel()
            call.updateBasketView()
            call.clearBasket()


        }
    }

    public interface CallBack {
        fun updateBasketView()
        fun clearBasket()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        call = context as ru.diitcenter.optovik.presentation.global.dialogscreen.DialogBasketFragment.CallBack
        }

}
