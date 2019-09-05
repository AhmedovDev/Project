package ru.diitcenter.optovik.presentation.screens.orderinfo.ui

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.activity_order_info.*
import kotlinx.android.synthetic.main.toolbar_order_info.*
import ru.diitcenter.optovik.presentation.screens.orderinfo.mvp.OrderInfoPresenter
import ru.diitcenter.optovik.presentation.screens.orderinfo.mvp.OrderInfoView
import ru.example.optovik.R
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Inject

class OrderInfoActivity : MvpAppCompatActivity(), OrderInfoView {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var basketHolder: ru.diitcenter.optovik.data.basketholder.BasketHolder

    @Inject
    @InjectPresenter
    lateinit var presenter: OrderInfoPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        ru.diitcenter.optovik.App.appComponent.orderInfoComponentBuilder()
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_info)
        info_constraint.visibility = View.VISIBLE
        check_order_info.visibility = View.VISIBLE
        bottom_buttons.visibility = View.VISIBLE
        initViews()
        update_order_info.setOnClickListener { presenter.getOrderInfo() }
        back_arrow_order_info.setOnClickListener { finish() }
        write_feedback.setOnClickListener { showBottomSheetDialogFragment() }
    }

    private fun initViews() {
        recycler_order_info.run {
            layoutManager = LinearLayoutManager(recycler_order_info.context)
//            addItemDecoration(
//                DividerItemDecoration(recycler_order_info.context, DividerItemDecoration.VERTICAL)
//            )
        }
    }

    private fun showBottomSheetDialogFragment() {
        val dialogfeedbackFragment =
            ru.diitcenter.optovik.presentation.global.dialogscreen.DialogFeedbackFragment()
        dialogfeedbackFragment.show(supportFragmentManager, dialogfeedbackFragment.tag)
    }

    override fun showProgress(progress: Boolean) {
        progressBar_order_info.visibility = if (progress) View.VISIBLE else View.GONE
    }

    override fun showProducts(basket: List<ru.diitcenter.optovik.data.global.models.Basket>) {
        val adapter = OrderInfoAdapter(basket)
        recycler_order_info.adapter = adapter
        adapter.setOnCategoryClickListener {
        }
    }

    override fun showOrderInfo(order: ru.diitcenter.optovik.data.global.models.Order) {
        order_id_order_info.text = "Заказ№ " + order.id.toString()
        status_order_info.text = order.status
        order_date_order_info.text = order.date
        all_price_order_info.text = order.finalPrice.toString()
        if (order.deliveryPrice == 0)
            diliviry_price_order_info.text = "бесплатно"
        else diliviry_price_order_info.text = order.deliveryPrice.toString() + " \u20BD"
        product_price_order_info.text = order.priceWithOutDelivery.toString()
    }

    override fun showError() {
        order_activity_container.visibility = View.VISIBLE
        info_constraint.visibility = View.GONE
        check_order_info.visibility = View.GONE
        bottom_buttons.visibility = View.GONE
    }

    override fun onBackPressed() {
        finish()
    }
}
