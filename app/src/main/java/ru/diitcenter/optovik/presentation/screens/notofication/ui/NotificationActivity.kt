package ru.diitcenter.optovik.presentation.screens.notofication.ui

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.activity_notification.*
import kotlinx.android.synthetic.main.toolbar_notification.*
import ru.diitcenter.optovik.presentation.screens.notofication.mvp.NotificationPresenter
import ru.diitcenter.optovik.presentation.screens.notofication.mvp.NotificationView
import ru.example.optovik.R
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Inject

class NotificationActivity : MvpAppCompatActivity(), NotificationView {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var basketHolder: ru.diitcenter.optovik.data.basketholder.BasketHolder

    @Inject
    @InjectPresenter
    lateinit var presenter: NotificationPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    private lateinit var navigator: Navigator


    override fun onCreate(savedInstanceState: Bundle?) {
        ru.diitcenter.optovik.App.appComponent.notificationComponentBuilder()
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

    override fun showNotification(notification: List<ru.diitcenter.optovik.data.global.models.Notification>) {
        val adapter = NotificationAdapter(notification)
        recycler_notification.adapter = adapter
    }

    override fun showError() {
        notification_container.visibility = View.VISIBLE
    }

    override fun onBackPressed() = finish()
}
