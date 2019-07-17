package com.example.optovik.presentation.screens.main.ui

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.optovik.App
import com.example.optovik.data.global.User
import com.example.optovik.presentation.screens.main.mvp.MainPresenter
import com.example.optovik.presentation.screens.main.mvp.MainView
import kotlinx.android.synthetic.main.activity_main.*
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Inject
import android.R
import android.support.v7.widget.LinearSnapHelper
import android.support.v7.widget.SnapHelper





class MainActivity : MvpAppCompatActivity(), MainView {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    @InjectPresenter
    lateinit var presenter: MainPresenter

    @ProvidePresenter
    fun providePresenter() = presenter



    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.mainComponentBuilder()
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
        setContentView(com.example.optovik.R.layout.activity_main)
        initViews()


    }

    private fun initViews() {
         category_recycler.run {
            layoutManager = LinearLayoutManager(category_recycler.context)
            addItemDecoration(
                DividerItemDecoration(category_recycler.context, DividerItemDecoration.VERTICAL)
            )
        }

        event_recycler.run {
            layoutManager = LinearLayoutManager(event_recycler.context,LinearLayoutManager.HORIZONTAL,false)
            val snapHelper = LinearSnapHelper()
            snapHelper.attachToRecyclerView(event_recycler)

        }
    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }


    override fun showProgress(show: Boolean) {
        progressBar.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun showCategories(users: List<User>) {
        val adapter = CategoryAdapter(users)
        category_recycler.adapter = adapter    }

    override fun showEvents(users: List<User>) {
        val adapter = EventAdapter(users)
        event_recycler.adapter = adapter
    }


    override fun onBackPressed() = presenter.onBackPressed()
}
