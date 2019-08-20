package com.example.optovik.presentation.screens.adresbook.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
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
import com.example.optovik.presentation.screens.catalog.mvp.CatalogPresenter
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

    @field:Inject
    lateinit var updateBasket: UpdateBasket

    private lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.adresbookComponentBuilder()
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adresbook)
    }

    override fun showProgress(progress: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLocations(locations: List<Location>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}
