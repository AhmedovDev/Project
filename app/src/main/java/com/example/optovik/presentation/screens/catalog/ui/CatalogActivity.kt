package com.example.optovik.presentation.screens.catalog.ui

import android.content.Intent
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
import com.example.optovik.data.global.models.Product
import com.example.optovik.presentation.global.BaseFragment
import com.example.optovik.presentation.global.utils.UpdateBasket
import com.example.optovik.presentation.screens.basket.ui.BasketActivity
import com.example.optovik.presentation.screens.catalog.mvp.CatalogPresenter
import com.example.optovik.presentation.screens.catalog.mvp.CatalogView
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_catalog.*
import kotlinx.android.synthetic.main.toolbar_catalog.*
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

class CatalogActivity : MvpAppCompatActivity(), CatalogView, View.OnClickListener {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var basket: BasketHolder

    @Inject
    @InjectPresenter
    lateinit var presenter: CatalogPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    @field:Inject
    lateinit var updateBasket: UpdateBasket

    private lateinit var navigator: Navigator

    lateinit var disposable: Disposable

    private val currentFragment
        get() = supportFragmentManager.findFragmentById(R.id.container_productcard) as BaseFragment?

    private var isFirstStart = true

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.catalogComponentBuilder()
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalog)
        initViews()
        backArrowClickListener()
        basketButtonClick()
        navigator = SupportAppNavigator(this, R.id.container_productcard)
//        disposable = updateBasket.subscribe().subscribe {
//            //Для обновления корзины
//            Log.e("DSFDSF", "DSAFFAS")
//        }

    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.update_catalog -> presenter.getAllCatalog()
        }
    }

    private fun initViews() {
        product_recycler.run {
            layoutManager = LinearLayoutManager(product_recycler.context)
            addItemDecoration(
                DividerItemDecoration(product_recycler.context, DividerItemDecoration.VERTICAL)
            )
        }
    }

    fun basketButtonClick() {
        button_green.setOnClickListener {
            val intent = Intent(this, BasketActivity::class.java)
            startActivity(intent)
        }
    }

    override fun showProgress(progress: Boolean) {
        progressBar_Catalog.visibility = if (progress) View.VISIBLE else View.GONE
    }

    override fun showProducts(products: List<Product>) {
        isFirstStart = false
        val adapter = CatalogAdapter(
            products = products,
            clickListenerPlus = { basket.addProduct(it)
            },
            clickListenerMinus = {
            basket.deleteProduct(it)
            },
            basket = basket

        )
        product_recycler.adapter = adapter
        adapter.setOnCatalogClickListener {
            presenter.gotoProducCard()
        }
    }

    override fun showInformation(information: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError() {
        constraintLayout2.visibility = View.GONE
        reletiv.visibility = View.GONE
        catalogactivity_container.visibility = View.VISIBLE
    }

    override fun visiblCatalog() {
        constraintLayout2.visibility = View.VISIBLE
        reletiv.visibility = View.VISIBLE
        catalogactivity_container.visibility = View.GONE

    }

    fun backArrowClickListener() {
        back_arrow.setOnClickListener { finish() }
    }

    override fun onResume() {
        super.onResume()
        if (!isFirstStart) presenter.getAllCatalog()
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

//    override fun onDestroy() {
//        super.onDestroy()
//        disposable.dispose()
//
//    }

    override fun onBackPressed() = currentFragment?.onBackPressed() ?: presenter.onBackPressed()

}
