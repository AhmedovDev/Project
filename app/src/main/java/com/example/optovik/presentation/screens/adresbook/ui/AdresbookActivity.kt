package com.example.optovik.presentation.screens.adresbook.ui

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
import com.example.optovik.data.global.models.Location
import com.example.optovik.presentation.global.utils.UpdateBasket
import com.example.optovik.presentation.screens.adresbook.mvp.AdresbookPresenter
import com.example.optovik.presentation.screens.adresbook.mvp.AdresbookView
import kotlinx.android.synthetic.main.activity_adresbook.*
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Inject

class AdresbookActivity : MvpAppCompatActivity(), AdresbookView {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var basket: BasketHolder

    @Inject
    @InjectPresenter
    lateinit var presenter: AdresbookPresenter

    @ProvidePresenter
    fun providePresenter() = presenter


    private lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.adresbookComponentBuilder()
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adresbook)
        initViews()
        update_adresbook.setOnClickListener { presenter.getAllLocations()
        adresbook_container.visibility = View.GONE}
    }

    private fun initViews() {
        recycler_adresbook.run {
            layoutManager = LinearLayoutManager(recycler_adresbook.context)
            addItemDecoration(
                DividerItemDecoration(recycler_adresbook.context, DividerItemDecoration.VERTICAL)
            )
        }
    }

    override fun showProgress(progress: Boolean) {
        progressBar_adresbook.visibility = if (progress) View.VISIBLE else View.GONE
    }

    override fun showLocations(locations: List<Location>) {
        val adapter = AdresbookAdapter(locations)
        recycler_adresbook.adapter = adapter
        adapter.setOnAdresClickListener {
            adapter.notifyDataSetChanged()
        }
    }

    override fun showError() {
        adresbook_container.visibility = View.VISIBLE
    }

    override fun onBackPressed() = presenter.onBackPressed()
}
