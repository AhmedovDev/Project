package com.example.optovik.presentation.screens.search.ui


import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.optovik.App

import com.example.optovik.R
import com.example.optovik.data.basketholder.BasketHolder
import com.example.optovik.data.global.models.Product
import com.example.optovik.presentation.global.BaseFragment
import com.example.optovik.presentation.screens.catalog.ui.CatalogAdapter
import com.example.optovik.presentation.screens.search.mvp.SearchPresenter
import com.example.optovik.presentation.screens.search.mvp.SearchView
import kotlinx.android.synthetic.main.activity_catalog.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_search.*
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Inject

class SearchFragment : BaseFragment(), SearchView {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var basket: BasketHolder

    @Inject
    @InjectPresenter
    lateinit var presenter: SearchPresenter

    @ProvidePresenter
    fun providePresenter() = presenter


    private lateinit var navigator: Navigator


    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.searchComponentBuilder()
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        back_arrow_search.setOnClickListener { presenter.back() }
        initViews()
        updateBasketButtonSearch()
        update_search.setOnClickListener { presenter.search("") }
        clear.setOnClickListener { input_search.setText("") }

        input_search.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                presenter.search(input_search.text.toString())
            }

        })

    }

    private fun initViews() {
        search_recycler.run {
            layoutManager = LinearLayoutManager(search_recycler.context)
            addItemDecoration(
                DividerItemDecoration(search_recycler.context, DividerItemDecoration.VERTICAL)
            )
        }
    }

    override fun showProgress(progress: Boolean) {
        progressBar_Search.visibility = if (progress) View.VISIBLE else View.GONE
    }

    override fun showFoundProducts(products: List<Product>) {
        Log.e("2222222", "2222222222")
        val adapter = CatalogAdapter(
            products = products,
            clickListenerPlus = {
                basket.addProduct(it)
//                updateBasketButton()
            },
            clickListenerMinus = {
                basket.deleteProduct(it)
//                updateBasketButton()
            },
            basket = basket

        )
        search_recycler.adapter = adapter
        adapter.setOnCatalogClickListener {
            presenter.goToProductCard(it)
        }
    }

    override fun updateBasketButtonSearch() {
        var priceAll: Int = 0
        basket.items.forEach { item ->
            priceAll += (item.product.price * item.quantity)
        }
        val haveItem = basket.items.filter {
            it.product.isEstimatedPrice == true
        }.firstOrNull()
        if (haveItem == null)
            isEstimatedPrise_search.visibility = View.GONE
        else
            isEstimatedPrise_search.visibility = View.VISIBLE

        price_on_button_search.setText("$priceAll")
        count_on_button_search.setText("${basket.items.size}")
    }

    override fun showError() {
        search_container.visibility = View.VISIBLE
    }

    override fun visiblSearchList() {
        search_container.visibility = View.GONE
    }

    override fun adapterUpdate() {
        (search_recycler.adapter as CatalogAdapter).notifyDataSetChanged()
    }


    override fun onBackPressed() {

    }

}
