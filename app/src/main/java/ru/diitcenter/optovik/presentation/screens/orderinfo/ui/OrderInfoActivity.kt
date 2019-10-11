package ru.diitcenter.optovik.presentation.screens.orderinfo.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.activity_order_info.*
import kotlinx.android.synthetic.main.toolbar_order_info.*
import ru.diitcenter.optovik.data.global.models.Basket
import ru.diitcenter.optovik.data.global.models.Product
import ru.diitcenter.optovik.data.prefs.PrefsHelper
import ru.diitcenter.optovik.presentation.global.dialogscreen.DialogFeedbackFragment
import ru.diitcenter.optovik.presentation.global.dialogscreen.DialogOrderRepeatFragment
import ru.diitcenter.optovik.presentation.screens.basket.ui.BasketActivity
import ru.diitcenter.optovik.presentation.screens.orderinfo.mvp.OrderInfoPresenter
import ru.diitcenter.optovik.presentation.screens.orderinfo.mvp.OrderInfoView
import ru.example.optovik.R
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Inject

class OrderInfoActivity : MvpAppCompatActivity(), OrderInfoView, DialogFeedbackFragment.CallBack,
    DialogOrderRepeatFragment.CallBackDialogOrderRepeat {

    var productsForOrder: List<Basket> = ArrayList()

    override fun replaceBasket() {
        basketHolder.clearBasketInServer()
        productsForOrder.forEach {
            basketHolder.addProductForReplaseOrder(it.product, it.quantity) {
                if (!it) {
                    showError()
                } else {
                    if (basketHolder.items.size == productsForOrder.size) {
                        val intent = Intent(this, BasketActivity::class.java)
                        startActivity(intent)
                    }

                }
            }
        }

    }


    override fun setFeedBack(rating: Int, review: String) {
        presenter.setFeedback(orderIdGlobal, rating, review)
    }

    var orderIdGlobal = 0

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var prefsHelper: PrefsHelper

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

        repeat_order.setOnClickListener { showBottomSheetDialogFragmentRepeatOrder() }
        presenter.getOrderInfo(intent.getIntExtra("order_id", 2))

        update_order_info.setOnClickListener {
            presenter.getOrderInfo(
                intent.getIntExtra(
                    "order_id",
                    2
                )
            )
            order_activity_container.visibility = View.GONE
        }
        back_arrow_order_info.setOnClickListener { finish() }

        write_feedback.setOnClickListener { showBottomSheetDialogFragment() }
    }

    private fun showBottomSheetDialogFragmentRepeatOrder() {
        val dialogOrderRepeatFragment =
            DialogOrderRepeatFragment()
        dialogOrderRepeatFragment.show(supportFragmentManager, dialogOrderRepeatFragment.tag)
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

    override fun showOrderInfo(
        order: ru.diitcenter.optovik.data.global.models.Order,
        productForOrder: List<Basket>
    ) {
        productsForOrder = productForOrder
        order_id_order_info.text = "Заказ№ " + order.id.toString()
        status_order_info.text = order.status
        order_date_order_info.text = order.date
        all_price_order_info.text = "%,d".format(order.finalPrice)
        if (order.deliveryPrice == 0)
            diliviry_price_order_info.text = "Бесплатно"
        else diliviry_price_order_info.text = order.deliveryPrice.toString() + " \u20BD"
        product_price_order_info.text = "%,d".format(order.priceWithOutDelivery)

        orderIdGlobal = order.id

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
