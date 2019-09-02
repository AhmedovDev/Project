package com.example.optovik.presentation.screens.notofication.ui

import android.support.v7.app.AppCompatActivity
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
import com.example.optovik.data.global.models.Notification
import com.example.optovik.presentation.screens.notofication.mvp.NotificationPresenter
import com.example.optovik.presentation.screens.notofication.mvp.NotificationView
import kotlinx.android.synthetic.main.activity_notification.*
import kotlinx.android.synthetic.main.toolbar_notification.*
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Inject

class NotificationActivity : MvpAppCompatActivity(), NotificationView {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var basketHolder: BasketHolder

    @Inject
    @InjectPresenter
    lateinit var presenter: NotificationPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    private lateinit var navigator: Navigator


    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.notificationComponentBuilder()
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)
        update_notification.setOnClickListener { presenter.getNotifications() }
        initViews()
        back_arrow_notification.setOnClickListener { finish() }
    }

    private fun initViews() {
        recycler_notification.run {
            layoutManager = LinearLayoutManager(recycler_notification.context)

        }
    }


    override fun showProgress(progress: Boolean) {
        progressBar_notification.visibility = if (progress) View.VISIBLE else View.GONE
    }

    override fun showNotification(notification: List<Notification>) {
        val adapter = NotificationAdapter(notification)
        recycler_notification.adapter = adapter
    }

    override fun showError() {
        notification_container.visibility = View.VISIBLE
    }

    override fun onBackPressed() = finish()
}
