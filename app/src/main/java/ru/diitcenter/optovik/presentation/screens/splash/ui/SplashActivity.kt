package ru.diitcenter.optovik.presentation.screens.splash.ui

import android.os.Bundle
import android.content.Intent
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import ru.diitcenter.optovik.presentation.screens.autorization.ui.AutorizationActivity
import ru.diitcenter.optovik.presentation.screens.main.ui.MainActivity
import ru.diitcenter.optovik.presentation.screens.splash.mvp.SplashPresenter
import ru.diitcenter.optovik.presentation.screens.splash.mvp.SplashView
import ru.example.optovik.R
import javax.inject.Inject


class SplashActivity : MvpAppCompatActivity(), SplashView {

    @Inject
    @InjectPresenter
    lateinit var presenter: SplashPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        ru.diitcenter.optovik.App.appComponent.splashComponentBuilder()
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    override fun intent() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun showError() {
        Toast.makeText(this, "Проблемы с интернетом", Toast.LENGTH_SHORT).show()
    }
}
