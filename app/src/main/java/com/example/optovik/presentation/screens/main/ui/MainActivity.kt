package com.example.optovik.presentation.screens.main.ui

import android.os.Bundle
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
import android.support.v7.widget.*
import android.widget.LinearLayout
import com.example.optovik.data.global.CategoryData
import com.example.optovik.data.global.EventData
import java.util.ArrayList


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
        //  initViews()

// todo Вернуть все как было после проверки дизайна
        val rv = findViewById<RecyclerView>(com.example.optovik.R.id.event_recycler)
        rv.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(event_recycler)
        event_recycler.addItemDecoration(
            CirclePagerIndicatorDecoration()
        )

        val data = ArrayList<EventData>()
        data.add(EventData(com.example.optovik.R.drawable.event_one))
        data.add(EventData(com.example.optovik.R.drawable.event_two))


        var adapter = EventCustomAdapter(data)
        event_recycler.adapter = adapter


        val rv2 = findViewById<RecyclerView>(com.example.optovik.R.id.category_recycler)
        rv2.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        category_recycler.addItemDecoration(
            DividerItemDecoration(
                category_recycler.context,
                DividerItemDecoration.VERTICAL
            )
        )

        val data2 = ArrayList<CategoryData>()
        data2.add(CategoryData(com.example.optovik.R.drawable.ic_fish, "Рыиное филе"))
        data2.add(CategoryData(com.example.optovik.R.drawable.ic_seaweed, "Водоросли"))
        data2.add(CategoryData(com.example.optovik.R.drawable.ic_fish, "Рыиное филе"))
        data2.add(CategoryData(com.example.optovik.R.drawable.ic_seaweed, "Водоросли"))
        data2.add(CategoryData(com.example.optovik.R.drawable.ic_fish, "Рыиное филе"))
        data2.add(CategoryData(com.example.optovik.R.drawable.ic_seaweed, "Водоросли"))
        data2.add(CategoryData(com.example.optovik.R.drawable.ic_fish, "Рыиное филе"))
        data2.add(CategoryData(com.example.optovik.R.drawable.ic_seaweed, "Водоросли"))
        data2.add(CategoryData(com.example.optovik.R.drawable.ic_fish, "Рыиное филе"))
        data2.add(CategoryData(com.example.optovik.R.drawable.ic_seaweed, "Водоросли"))
        data2.add(CategoryData(com.example.optovik.R.drawable.ic_fish, "Рыиное филе"))
        data2.add(CategoryData(com.example.optovik.R.drawable.ic_seaweed, "Водоросли"))
        data2.add(CategoryData(com.example.optovik.R.drawable.ic_fish, "Рыиное филе"))
        data2.add(CategoryData(com.example.optovik.R.drawable.ic_seaweed, "Водоросли"))
        data2.add(CategoryData(com.example.optovik.R.drawable.ic_fish, "Рыиное филе"))
        data2.add(CategoryData(com.example.optovik.R.drawable.ic_seaweed, "Водоросли"))
        data2.add(CategoryData(com.example.optovik.R.drawable.ic_fish, "Рыиное филе"))
        data2.add(CategoryData(com.example.optovik.R.drawable.ic_seaweed, "Водоросли"))



        var adapter2 = CategotyCustomAdapter(data2)
        category_recycler.adapter = adapter2
    }

    //Основная реализация
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
    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

// todo Основная реальзация


//    override fun showProgress(show: Boolean) {
//        progressBar.visibility = if (show) View.VISIBLE else View.GONE
//    }

//    override fun showCategories(users: List<User>) {
//        val adapter = CategoryAdapter(users)
//        category_recycler.adapter = adapter
//    }

//    override fun showEvents(users: List<User>) {
//        val adapter = EventAdapter(users)
//        event_recycler.adapter = adapter
//
//    }


    override fun onBackPressed() = finish()
}



