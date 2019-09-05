package ru.diitcenter.optovik.presentation.screens.checkorder.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.activity_check_order.*
import kotlinx.android.synthetic.main.activity_check_order.rubl
import kotlinx.android.synthetic.main.toolbar_check_order.*
import ru.diitcenter.optovik.presentation.screens.adresbook.ui.AdresbookActivity
import ru.diitcenter.optovik.presentation.screens.checkorder.mvp.CheckOrderPresenter
import ru.diitcenter.optovik.presentation.screens.checkorder.mvp.CheckOrderView
import ru.example.optovik.R
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Inject

class CheckOrderActivity : MvpAppCompatActivity(), CheckOrderView {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var basket: ru.diitcenter.optovik.data.basketholder.BasketHolder

    @Inject
    @InjectPresenter
    lateinit var presenter: CheckOrderPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    @Inject
    lateinit var prefsHelper: ru.diitcenter.optovik.data.prefs.PrefsHelper

    @Inject
    lateinit var basketHolder: ru.diitcenter.optovik.data.basketholder.BasketHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        ru.diitcenter.optovik.App.appComponent.checkOrderComponentBuilder()
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_order)

        delivery_address.isFocusable = true
        select_address.setOnClickListener {
            val intent = Intent(this, AdresbookActivity::class.java)
            startActivity(intent)
        }
        back_arrow_check_order.setOnClickListener { finish() }

        if(!prefsHelper.getAddress().isNullOrEmpty()) {
            address_check_order.text = prefsHelper.getAddress()
            connect_number.text = prefsHelper.getPhone()
            delivery_address.visibility = View.VISIBLE
            address_check_order.visibility = View.VISIBLE
        }
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

        if(!prefsHelper.getAddress().isNullOrEmpty()) {
            address_check_order.text = prefsHelper.getAddress()
            connect_number.text = prefsHelper.getPhone()
            delivery_address.visibility = View.VISIBLE
            address_check_order.visibility = View.VISIBLE
        }
    }
}
