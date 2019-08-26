package com.example.optovik.presentation.screens.main.ui

import android.content.Intent
import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.optovik.App
import com.example.optovik.presentation.screens.main.mvp.MainPresenter
import com.example.optovik.presentation.screens.main.mvp.MainView
import kotlinx.android.synthetic.main.activity_main.*
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Inject
import android.support.v7.widget.*
import android.view.View
import com.example.optovik.AutorizationActivity
import com.example.optovik.R
import com.example.optovik.data.basketholder.BasketHolder
import com.example.optovik.data.global.models.Category
import com.example.optovik.data.global.models.Event
import com.example.optovik.presentation.global.BaseFragment
import com.example.optovik.presentation.screens.basket.ui.BasketActivity
import com.example.optovik.presentation.screens.catalog.ui.CatalogActivity
import com.example.optovik.presentation.screens.myorder.ui.MyOrderActivity
import com.example.optovik.presentation.screens.notofication.ui.NotificationActivity
import kotlinx.android.synthetic.main.activity_catalog.*
import kotlinx.android.synthetic.main.toolbar_main.*
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.android.support.SupportAppNavigator


class MainActivity : MvpAppCompatActivity(), MainView {


    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var basketHolder: BasketHolder

    @Inject
    @InjectPresenter
    lateinit var presenter: MainPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    private lateinit var navigator: Navigator

    private val currentFragment
        get() = supportFragmentManager.findFragmentById(R.id.container_main_activity) as BaseFragment?

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.mainComponentBuilder()
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
        setContentView(com.example.optovik.R.layout.activity_main)
//        presenter.getBasket()
        initViews()
        updateBasketButtonMain()
        navigator = SupportAppNavigator(this, R.id.container_main_activity)
        button_basket_main.setOnClickListener {
            val intent = Intent(this, BasketActivity::class.java)
            startActivity(intent)
        }
        order_main.setOnClickListener {
            val intent = Intent(this, MyOrderActivity::class.java)
            startActivity(intent)
        }
        order_main_one.setOnClickListener { }
        search_main.setOnClickListener { presenter.onSearchClick() }
        notification.setOnClickListener {
            val intent = Intent(this, NotificationActivity::class.java)
            startActivity(intent)
        }

    }

    override fun updateBasketButtonMain() {
        var priceAll: Int = 0
        basketHolder.items.forEach { item ->
            priceAll += (item.product.price * item.quantity)
        }
        val haveItem = basketHolder.items.filter {
            it.product.isEstimatedPrice == true
        }.firstOrNull()
        if (haveItem == null)
            isEstimatedPrise_main.visibility = View.GONE
        else
            isEstimatedPrise_main.visibility = View.VISIBLE

        price_on_button_main.setText("$priceAll")
        count_on_button_main.setText("${basketHolder.items.size}")
    }

    // настройка визуального представления recycler-ов
    private fun initViews() {
        category_recycler.run {
            layoutManager = LinearLayoutManager(category_recycler.context)
            addItemDecoration(
                DividerItemDecoration(category_recycler.context, DividerItemDecoration.VERTICAL)
            )
        }

        event_recycler.run {
            layoutManager = LinearLayoutManager(event_recycler.context, LinearLayoutManager.HORIZONTAL, false)
            val snapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(event_recycler)
            addItemDecoration(
                CirclePagerIndicatorDecoration()
            )
        }
        updateClick()
        updateBasketButtonMain()
    }

    override fun categoryesClick() {

    }

    fun updateClick() {
        update.setOnClickListener { presenter.getAllData() }
    }

    override fun showError() {
        nestedScrollView.visibility = View.GONE
        mainactivity_container.visibility = View.VISIBLE
    }

    override fun visiblMain() {
        nestedScrollView.visibility = View.VISIBLE
        mainactivity_container.visibility = View.GONE
    }


    override fun showProgress(show: Boolean) {
        progressBar.visibility = if (show) View.VISIBLE else View.GONE
        category.visibility = if (show) View.GONE else View.VISIBLE
        event_recycler.visibility = if (show) View.GONE else View.VISIBLE

    }

    override fun showCategories(category: List<Category>) {
        val adapter = CategoryAdapter(category)
        category_recycler.adapter = adapter
        adapter.setOnCategoryClickListener {
            val intent = Intent(this, CatalogActivity::class.java)
            startActivity(intent)
        }
    }

    override fun showEvents(event: List<Event>) {

        val adapter = EventAdapter(event)
        event_recycler.adapter = adapter
        adapter.setOnEventClickListener {
            val haveItem = basketHolder.items.filter { item ->
                item.product.id == it.idProduct
            }.firstOrNull()
            if (haveItem != null) {
                presenter.onEventClick(haveItem.product)
            }
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        updateBasketButtonMain()
    }

    override fun onBackPressed() = presenter.onBackPressed()
}



