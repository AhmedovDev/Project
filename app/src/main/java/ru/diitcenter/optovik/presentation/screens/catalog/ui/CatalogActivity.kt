package ru.diitcenter.optovik.presentation.screens.catalog.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_catalog.*
import kotlinx.android.synthetic.main.activity_catalog.constraintLayout2
import kotlinx.android.synthetic.main.activity_catalog.reletiv
import kotlinx.android.synthetic.main.toolbar_catalog.*
import ru.diitcenter.optovik.presentation.global.utils.UpdateBasket
import ru.diitcenter.optovik.presentation.screens.basket.ui.BasketActivity
import ru.diitcenter.optovik.presentation.screens.catalog.mvp.CatalogPresenter
import ru.diitcenter.optovik.presentation.screens.catalog.mvp.CatalogView
import ru.example.optovik.R
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

class CatalogActivity : MvpAppCompatActivity(), CatalogView, View.OnClickListener {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var basket: ru.diitcenter.optovik.data.basketholder.BasketHolder

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
        get() = supportFragmentManager.findFragmentById(R.id.container_productcard) as ru.diitcenter.optovik.presentation.global.BaseFragment?

    private var isFirstStart = true
    private var loaderCounter: Int = 0
        set(value) {
            field = value

            if (value > 0) {
                //Блокировать кнопку
            } else {
                //Разблокировать кнопку
            }
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        ru.diitcenter.optovik.App.appComponent.catalogComponentBuilder()
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalog)
        var categoryId = intent.getIntExtra("category_id", 1)
        Log.e("CATEGORY_ID","$categoryId")
        presenter.getAllCatalog(categoryId)
        updateBasketButton()
        initViews()
        backArrowClickListener()
        basketButtonClick()
        search_catalog.setOnClickListener { presenter.searchCatalogClick() }
        navigator = SupportAppNavigator(this, R.id.container_productcard)
//        disposable = updateBasket.subscribe().subscribe {
//            //Для обновления корзины
//            Log.e("DSFDSF", "DSAFFAS")
//        }
        namecategory.text = intent.getStringExtra("nameCategory")
        emptyBasketCheck()
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.update_catalog -> presenter.getAllCatalog(intent.getIntExtra("category_id", 1))
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

    override fun emptyBasketCheck() {
        if(basket.items.isEmpty())
            button_green.visibility = View.GONE
        else
            button_green.visibility = View.VISIBLE
    }


    override fun updateBasketButton() {
        var priceAll : Int = 0
        basket.items.forEach { item ->
            priceAll +=  (item.product.price * item.quantity)
        }
        val haveItem = basket.items.filter {
            it.product.isEstimatedPrice == true
        }.firstOrNull()
        if (haveItem == null)
            isEstimatedPrise_catalog.visibility = View.GONE

          //  isEstimatedPrise_catalog.visibility = View.VISIBLE
        price_on_button.setText("%,d".format(priceAll))
        count_on_button.setText("${basket.items.size}")
    }

    override fun showProgress(progress: Boolean) {
        progressBar_Catalog.visibility = if (progress) View.VISIBLE else View.GONE
    }

    override fun showProducts(products: List<ru.diitcenter.optovik.data.global.models.Product>) {
        isFirstStart = false
        val adapter = CatalogAdapter(
            products = products,
            clickListenerPlus = {

//                loaderCounter += 1
//               basket.addProduct(it) {
//                   loaderCounter -= 1
//               }
               // basket.synchronizeBasketWithServer()
                updateBasketButton()
                emptyBasketCheck()
            },
            clickListenerMinus = {
     //           basket.deleteProduct(it)
               // basket.synchronizeBasketWithServer()
                updateBasketButton()
                emptyBasketCheck()
            },
            basket = basket

        )
        product_recycler.adapter = adapter
        adapter.setOnCatalogClickListener {
            presenter.gotoProducCard(it.id)
        }
    }

    override fun adapterUpdate() {
        (product_recycler.adapter as CatalogAdapter).notifyDataSetChanged()

    }

    override fun showInformation(information: String) {
        information_catalog.text = information
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
        if (!isFirstStart) presenter.getAllCatalog(intent.getIntExtra("category_id", 1))
        updateBasketButton()
        emptyBasketCheck()
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
        presenter.getAllCatalog(intent.getIntExtra("category_id", 1))

    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onStart() {
        super.onStart()
        presenter.getAllCatalog(intent.getIntExtra("category_id", 1))
    }

//    override fun onDestroy() {
//        super.onDestroy()
//        disposable.dispose()
//
//    }

    override fun onBackPressed() = currentFragment?.onBackPressed() ?: presenter.onBackPressed()

}
