package com.example.optovik.presentation.screens.myorder.ui

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.optovik.App
import com.example.optovik.R
import com.example.optovik.data.basketholder.BasketHolder
import com.example.optovik.data.global.models.MyOrder
import com.example.optovik.data.prefs.PrefsHelper
import com.example.optovik.presentation.screens.main.mvp.MainPresenter
import com.example.optovik.presentation.screens.myorder.mvp.MyOrderPresenter
import com.example.optovik.presentation.screens.myorder.mvp.MyOrderView
import kotlinx.android.synthetic.main.activity_my_order.*
import kotlinx.android.synthetic.main.toolbar_my_order.*
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Inject

class MyOrderActivity : MvpAppCompatActivity(), MyOrderView {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var basketHolder: BasketHolder

    @Inject
    @InjectPresenter
    lateinit var presenter: MyOrderPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    private lateinit var navigator: Navigator

    @Inject
    lateinit var prefsHelper: PrefsHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.myOrderComponentBuilder()
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_order)
        initViews()
        back_arrow_my_order.setOnClickListener { finish() }
        update_my_order.setOnClickListener { presenter.getMyOrders() }
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

    override fun showMyOrders(myOrder: List<MyOrder>) {
        val adapter = MyOrderAdapter(myOrder)
        my_order_recycler.adapter = adapter
        adapter.setOnAdresClickListener {

        }
    }

    override fun showError() {
        my_order_container.visibility = View.VISIBLE
    }

    override fun onBackPressed() = finish()

}
