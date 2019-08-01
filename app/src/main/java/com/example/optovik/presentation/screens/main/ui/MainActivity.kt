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
import com.example.optovik.R
import com.example.optovik.data.global.models.Category
import com.example.optovik.data.global.models.Event
import com.example.optovik.presentation.global.BaseFragment
import com.example.optovik.presentation.screens.catalog.ui.CatalogActivity
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.android.support.SupportAppNavigator


class MainActivity : MvpAppCompatActivity(), MainView {


    @Inject
    lateinit var navigatorHolder: NavigatorHolder

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
        initViews()
        navigator = SupportAppNavigator(this, R.id.container_main_activity)

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
        nestedScrollView.visibility =View.VISIBLE
        mainactivity_container.visibility = View.GONE
    }


    override fun showProgress(show: Boolean) {
        progressBar.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun showCategories(category: List<Category>) {
        val adapter = CategoryAdapter(category)
        category_recycler.adapter = adapter
        adapter.setOnCategoryClickListener { val intent = Intent(this,CatalogActivity::class.java)
            startActivity(intent) }
    }

    override fun showEvents(event: List<Event>) {
        val adapter = EventAdapter(event)
        event_recycler.adapter = adapter
        adapter.setOnEventClickListener { presenter.onEventClick() }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onBackPressed() = presenter.onBackPressed()
}



