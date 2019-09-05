package ru.diitcenter.optovik.presentation.screens.autorization.ui

import android.annotation.SuppressLint
import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import ru.diitcenter.optovik.App
import ru.diitcenter.optovik.presentation.global.BaseFragment
import ru.diitcenter.optovik.presentation.screens.autorization.mvp.AutorizationPresenter
import ru.diitcenter.optovik.presentation.screens.autorization.mvp.AutorizationView
import ru.example.optovik.R
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

@SuppressLint("Registered")
class AutorizationActivity : MvpAppCompatActivity(), AutorizationView {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    @InjectPresenter
    lateinit var presenter: AutorizationPresenter

    private lateinit var navigator: Navigator

    private val currentFragment
        get() = supportFragmentManager.findFragmentById(R.id.container) as BaseFragment?

    @ProvidePresenter
    fun providePresenter() = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
      App.appComponent.autorizationComponentBuilder()
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_autorization)
        navigator = SupportAppNavigator(this, R.id.container)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onBackPressed() =  currentFragment?.onBackPressed() ?: presenter.onBackPressed()
}
