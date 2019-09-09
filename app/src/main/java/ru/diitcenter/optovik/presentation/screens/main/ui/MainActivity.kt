package ru.diitcenter.optovik.presentation.screens.main.ui

import android.content.Intent
import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.activity_main.*
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Inject
import android.support.v7.widget.*
import android.view.View
import kotlinx.android.synthetic.main.toolbar_main.*
import ru.diitcenter.optovik.presentation.global.utils.hideKeyboard
import ru.diitcenter.optovik.presentation.screens.basket.ui.BasketActivity
import ru.diitcenter.optovik.presentation.screens.catalog.ui.CatalogActivity
import ru.diitcenter.optovik.presentation.screens.main.mvp.MainPresenter
import ru.diitcenter.optovik.presentation.screens.main.mvp.MainView
import ru.diitcenter.optovik.presentation.screens.myorder.ui.MyOrderActivity
import ru.diitcenter.optovik.presentation.screens.notofication.ui.NotificationActivity
import ru.diitcenter.optovik.presentation.screens.orderinfo.ui.OrderInfoActivity
import ru.example.optovik.R
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.android.support.SupportAppNavigator


class MainActivity : MvpAppCompatActivity(), MainView {


    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var basketHolder: ru.diitcenter.optovik.data.basketholder.BasketHolder

    @Inject
    @InjectPresenter
    lateinit var presenter: MainPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    private lateinit var navigator: Navigator

    private val currentFragment
        get() = supportFragmentManager.findFragmentById(R.id.container_main_activity) as ru.diitcenter.optovik.presentation.global.BaseFragment?

    override fun onCreate(savedInstanceState: Bundle?) {
        ru.diitcenter.optovik.App.appComponent.mainComponentBuilder()
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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

        order_main_one.setOnClickListener {   val intent = Intent(this, OrderInfoActivity::class.java)
            startActivity(intent) }
        emptyBasketCheck()

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

        price_on_button_main.setText("%,d".format(priceAll))
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
                ru.diitcenter.optovik.presentation.screens.main.ui.CirclePagerIndicatorDecoration()
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

    override fun showCategories(category: List<ru.diitcenter.optovik.data.global.models.Category>) {
        val adapter = CategoryAdapter(category)
        category_recycler.adapter = adapter
        adapter.setOnCategoryClickListener {
            val intent = Intent(this, CatalogActivity::class.java)
            intent.putExtra("nameCategory", "${it.name}")
            startActivity(intent)
        }
    }

    override fun showLastOrder(lastOrder: ru.diitcenter.optovik.data.global.models.MyOrder) {
        order_id_main.text = "Заказ №" + lastOrder.id.toString()
        sum_main.text = "%,d".format(lastOrder.sum) + " \u20BD"
        title_main.text = lastOrder.title
        order_date_main.text = lastOrder.date
    }

    override fun showEvents(event: List<ru.diitcenter.optovik.data.global.models.Event>) {

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

    override fun emptyBasketCheck() {
        if(basketHolder.items.isEmpty())
            button_basket_main.visibility = View.GONE
        else
            button_basket_main.visibility = View.VISIBLE
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
        emptyBasketCheck()

    }

    override fun onBackPressed() = presenter.onBackPressed()
}



