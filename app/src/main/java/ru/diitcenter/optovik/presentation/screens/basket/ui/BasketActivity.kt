package ru.diitcenter.optovik.presentation.screens.basket.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.activity_basket.*
import kotlinx.android.synthetic.main.activity_catalog.*
import kotlinx.android.synthetic.main.activity_catalog.reletiv
import kotlinx.android.synthetic.main.activity_catalog.update_catalog
import kotlinx.android.synthetic.main.toolbar_basker.*
import ru.diitcenter.optovik.presentation.screens.basket.mvp.BasketPresenter
import ru.diitcenter.optovik.presentation.screens.basket.mvp.BasketView
import ru.diitcenter.optovik.presentation.screens.catalog.ui.CatalogActivity
import ru.diitcenter.optovik.presentation.screens.checkorder.ui.CheckOrderActivity
import ru.example.optovik.R
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject


class BasketActivity : MvpAppCompatActivity(), BasketView,
    ru.diitcenter.optovik.presentation.global.dialogscreen.DialogBasketFragment.CallBack {


    var freeDeliveryPrice = 0

    override fun updateBasketView() {
        presenter.getBasket()
        basketResultPriceCheсk()
        basketEmptyCheck()
    }

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    @InjectPresenter
    lateinit var presenter: BasketPresenter

    @Inject
    lateinit var basketHolder: ru.diitcenter.optovik.data.basketholder.BasketHolder

    @ProvidePresenter
    fun providePresenter() = presenter

    private lateinit var navigator: Navigator

    private val currentFragment
        get() = supportFragmentManager.findFragmentById(R.id.container_basket) as ru.diitcenter.optovik.presentation.global.BaseFragment?


    override fun onCreate(savedInstanceState: Bundle?) {
        ru.diitcenter.optovik.App.appComponent.basketComponentBuilder()
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basket)
        initViews()
        backArrowClickListener()
        basketEmptyCheck()
        navigator = SupportAppNavigator(this, R.id.container_basket)
        delete_basket.setOnClickListener {
            showBottomSheetDialogFragment()
        }
        check_order_basket.setOnClickListener {
            val intent = Intent(this, CheckOrderActivity::class.java)
            startActivity(intent)
        }

        basketResultPriceCheсk()
    }

    private fun basketEmptyCheck() {
        if (basketHolder.items.size == 0) {
            basletactivity_empty_container.visibility = View.VISIBLE
            delete_basket.visibility = View.GONE
            reletiv.visibility = View.GONE
            check_order_basket.visibility = View.GONE

        } else {
            basletactivity_empty_container.visibility = View.GONE
            delete_basket.visibility = View.VISIBLE
            reletiv.visibility = View.VISIBLE
            check_order_basket.visibility = View.VISIBLE

        }
        go_to_catalog.setOnClickListener {
            startActivity(Intent(this, CatalogActivity::class.java))
        }

    }

    private fun showBottomSheetDialogFragment() {
        val dialogBasketFragment =
            ru.diitcenter.optovik.presentation.global.dialogscreen.DialogBasketFragment()
        dialogBasketFragment.show(supportFragmentManager, dialogBasketFragment.tag)
        basketEmptyCheck()
    }

    fun closeCheck() {
    }

    private fun basketResultPriceCheсk() {
        product_price.setText(price().toString())
        if (price() < freeDeliveryPrice) {
            all_price.setText((price() + 100).toString())
            diliviry_price.setText("100")
        } else {
            rubl.visibility = View.GONE
            diliviry_price.setText("бесплатная")
            all_price.setText(price().toString())
        }
        if (price() == 0)
            all_price.setText(price().toString())
    }

    private fun initViews() {
        basket_recycler.run {
            layoutManager = LinearLayoutManager(basket_recycler.context)
            addItemDecoration(
                DividerItemDecoration(basket_recycler.context, DividerItemDecoration.VERTICAL)
            )
        }
        updateClick()
    }

    fun updateClick() {
        update_catalog.setOnClickListener { presenter.getBasket() }
    }

    override fun showProgress(progress: Boolean) {
        progressBar_basket.visibility = if (progress) View.VISIBLE else View.GONE
    }

    override fun showBasket(basket: List<ru.diitcenter.optovik.data.global.models.Basket>) {
        val adapter = BasketAdapter(
            basketholder = basketHolder,
            clickListenerPlus = {
                basketHolder.addProduct(it)
                basketResultPriceCheсk()
            },
            clickListenerMinus = {
                basketHolder.deleteProduct(it)
                basketResultPriceCheсk()
                basketEmptyCheck()
            },
            clickListenerdrop = {
                basketHolder.dropProduct(it)
                basketResultPriceCheсk()
                basketEmptyCheck()

            })
        basket_recycler.adapter = adapter
        adapter.setOnBasketClickListener {
            container_basket.visibility = View.VISIBLE
            presenter.gotoProductCard(it)
        }

    }

  


    fun deleteBasket() {
        basketHolder.items.removeAll(basketHolder.items)
    }

    override fun showInformation(information: String) {
        information_basket.text = "При заказе на сумму от " + information + " доставка бесплатная."
        freeDeliveryPrice = information.toInt()
    }

    override fun showError() {
        basket_nestedscroll.visibility = View.GONE
        reletiv.visibility = View.GONE
        basletactivity_container.visibility = View.VISIBLE
    }

    override fun visiblBasket() {
        basket_nestedscroll.visibility = View.VISIBLE
        reletiv.visibility = View.VISIBLE
        basletactivity_container.visibility = View.GONE

    }

    fun backArrowClickListener() {
        back_arrow_basket.setOnClickListener { finish() }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
        basketResultPriceCheсk()
        basketEmptyCheck()
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()

    }

    override fun onResume() {
        super.onResume()
        presenter.getBasket()
        basketResultPriceCheсk()
        basketEmptyCheck()
    }

    fun price(): Int {
        var productPrice = 0
        basketHolder.items.forEach { productPrice += (it.product.price * it.quantity) }
        if (basketHolder.items.size == 0)
            check.visibility = View.GONE
        return productPrice
    }

//    override fun onDestroy() {
//        super.onDestroy()
//        disposable.dispose()
//
//    }

    override fun onBackPressed() = currentFragment?.onBackPressed() ?: presenter.onBackPressed()

}
