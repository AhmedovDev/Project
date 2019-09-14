package ru.diitcenter.optovik.presentation.screens.myorder.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.activity_my_order.*
import kotlinx.android.synthetic.main.toolbar_my_order.*
import ru.diitcenter.optovik.data.global.models.Basket
import ru.diitcenter.optovik.presentation.global.dialogscreen.DialogOrderRepeatFragment
import ru.diitcenter.optovik.presentation.screens.myorder.mvp.MyOrderPresenter
import ru.diitcenter.optovik.presentation.screens.myorder.mvp.MyOrderView
import ru.diitcenter.optovik.presentation.screens.orderinfo.ui.OrderInfoActivity
import ru.example.optovik.R
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Inject

class MyOrderActivity : MvpAppCompatActivity(), MyOrderView , DialogOrderRepeatFragment.CallBackDialogOrderRepeat{

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var basketHolder: ru.diitcenter.optovik.data.basketholder.BasketHolder

    @Inject
    @InjectPresenter
    lateinit var presenter: MyOrderPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    private lateinit var navigator: Navigator

    private var orderProdeucts: List<Basket> = ArrayList()
    private var orderId: Int = 0

    @Inject
    lateinit var prefsHelper: ru.diitcenter.optovik.data.prefs.PrefsHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        ru.diitcenter.optovik.App.appComponent.myOrderComponentBuilder()
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_order)
        initViews()
        back_arrow_my_order.setOnClickListener { finish() }
        update_my_order.setOnClickListener { presenter.getMyOrders() }
    }


    private fun showBottomSheetDialogFragment() {
        val dialogOrderRepeatFragment =
            DialogOrderRepeatFragment()
        dialogOrderRepeatFragment.show(supportFragmentManager, dialogOrderRepeatFragment.tag)
    }

    private fun initViews() {
        my_order_recycler.run {
            layoutManager = LinearLayoutManager(my_order_recycler.context)
            addItemDecoration(
                DividerItemDecoration(my_order_recycler.context, DividerItemDecoration.VERTICAL)
            )
        }
    }

    override fun showProgress(progress: Boolean) {
        progressBar_my_order.visibility = if (progress) View.VISIBLE else View.GONE

    }

    override fun showMyOrders(myOrder: List<ru.diitcenter.optovik.data.global.models.MyOrder>) {
        val adapter = MyOrderAdapter(myOrder)
        my_order_recycler.adapter = adapter
        adapter.setOnAdresClickListener(
            listener = {
                val intent2 = Intent(this, OrderInfoActivity::class.java)
                intent2.putExtra("order_id", it.id)
                startActivity(intent2)
            },
            listenerRepeat = {
                showBottomSheetDialogFragment()
                orderId = it.id

            }
        )
    }




    override fun replaceBasket() {
        presenter.replaseBasket(orderId)
    }

    override fun showError() {
        my_order_container.visibility = View.VISIBLE
    }

    override fun onBackPressed() = finish()

}
