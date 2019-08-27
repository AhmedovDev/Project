package com.example.optovik.presentation.screens.checkorder.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.optovik.App
import com.example.optovik.R
import com.example.optovik.data.basketholder.BasketHolder
import com.example.optovik.data.prefs.PrefsHelper
import com.example.optovik.presentation.screens.adresbook.ui.AdresbookActivity
import com.example.optovik.presentation.screens.checkorder.mvp.CheckOrderPresenter
import com.example.optovik.presentation.screens.checkorder.mvp.CheckOrderView
import com.example.optovik.presentation.screens.myorder.ui.MyOrderActivity
import kotlinx.android.synthetic.main.activity_basket.*
import kotlinx.android.synthetic.main.activity_check_order.*
import kotlinx.android.synthetic.main.activity_check_order.rubl
import kotlinx.android.synthetic.main.toolbar_check_order.*
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Inject
import kotlinx.android.synthetic.main.activity_basket.check as check1

class CheckOrderActivity : MvpAppCompatActivity(), CheckOrderView {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var basket: BasketHolder

    @Inject
    @InjectPresenter
    lateinit var presenter: CheckOrderPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    @Inject
    lateinit var prefsHelper: PrefsHelper

    @Inject
    lateinit var basketHolder: BasketHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.checkOrderComponentBuilder()
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_order)

        select_address.setOnClickListener {
            val intent = Intent(this, AdresbookActivity::class.java)
            startActivity(intent)
        }
        back_arrow_check_order.setOnClickListener { finish() }

        if(prefsHelper.getAddress() != "") {
        address_check_order.text = prefsHelper.getAddress()
        connect_number.text = prefsHelper.getPhone()}
        basketResultPriceCheсk()
    }

    private fun basketResultPriceCheсk() {
        product_price_check_order.setText(price().toString())
        if (price() < 3000) {
            all_price_check_order.setText((price() + 100).toString())
            diliviry_price_check_order.setText("100")
        } else {
            rubl.visibility = View.GONE
            diliviry_price_check_order.setText("бесплатная")
            all_price_check_order.setText(price().toString())
        }
        if (price() == 0)
            all_price_check_order.setText(price().toString())
    }


    fun price(): Int {
        var productPrice = 0
        basketHolder.items.forEach { productPrice += (it.product.price * it.quantity) }
        if (basketHolder.items.size == 0)
            check.visibility = View.GONE
        return productPrice
    }


    override fun onResume() {
        super.onResume()
        address_check_order.text = prefsHelper.getAddress()
        connect_number.text = prefsHelper.getPhone()
    }
}
