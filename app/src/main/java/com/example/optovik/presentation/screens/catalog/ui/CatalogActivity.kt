package com.example.optovik.presentation.screens.catalog.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import android.view.View
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.optovik.App
import com.example.optovik.R
import com.example.optovik.data.global.models.Products
import com.example.optovik.presentation.screens.catalog.mvp.CatalogPresenter
import com.example.optovik.presentation.screens.catalog.mvp.CatalogView
import com.example.optovik.presentation.screens.main.mvp.MainPresenter
import com.example.optovik.presentation.screens.main.ui.CirclePagerIndicatorDecoration
import com.example.optovik.presentation.screens.main.ui.EventAdapter
import kotlinx.android.synthetic.main.activity_catalog.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_autorization_with_arrow.*
import kotlinx.android.synthetic.main.toolbar_catalog.*
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Inject

class CatalogActivity : MvpAppCompatActivity(), CatalogView {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    @InjectPresenter
    lateinit var presenter: CatalogPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    private lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.catalogComponentBuilder()
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalog)
        initViews()
        backArrowClickListener()
    }

    private fun initViews() {
        product_recycler.run {
            layoutManager = LinearLayoutManager(product_recycler.context)
            addItemDecoration(
                DividerItemDecoration(product_recycler.context, DividerItemDecoration.VERTICAL)
            )
        }
        updateClick()
    }

    fun updateClick() {
        update_catalog.setOnClickListener { presenter.getAllCatalog() }
    }

    override fun showProgress(progress: Boolean) {
        progressBar_Catalog.visibility = if (progress) View.VISIBLE else View.GONE
    }

    override fun showProducts(products: List<Products>) {
        val adapter = CatalogAdapter(products)
        product_recycler.adapter = adapter
    }

    override fun showInformation(information: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError() {
        constraintLayout2.visibility = View.GONE
        constraintLayout.visibility = View.GONE
        catalogactivity_container.visibility = View.VISIBLE
    }

    override fun visiblCatalog() {
        constraintLayout2.visibility = View.VISIBLE
        constraintLayout.visibility = View.VISIBLE
        catalogactivity_container.visibility = View.GONE
    }

    fun backArrowClickListener() {
        back_arrow.setOnClickListener { finish() }
    }

    override fun onBackPressed() {
        finish()
    }

}
