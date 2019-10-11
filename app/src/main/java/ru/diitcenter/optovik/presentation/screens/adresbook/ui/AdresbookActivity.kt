package ru.diitcenter.optovik.presentation.screens.adresbook.ui

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.activity_adresbook.*
import kotlinx.android.synthetic.main.toolbar_adresbook.*
import ru.diitcenter.optovik.presentation.screens.adresbook.mvp.AdresbookPresenter
import ru.diitcenter.optovik.presentation.screens.adresbook.mvp.AdresbookView
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Inject
import android.content.Intent
import android.R
import android.net.Uri
import retrofit2.http.Url


class AdresbookActivity : MvpAppCompatActivity(), AdresbookView {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var basket: ru.diitcenter.optovik.data.basketholder.BasketHolder

    @Inject
    @InjectPresenter
    lateinit var presenter: AdresbookPresenter

    @ProvidePresenter
    fun providePresenter() = presenter


    private lateinit var navigator: Navigator

    private var operatorNumber = ""
    @Inject
    lateinit var prefsHelper: ru.diitcenter.optovik.data.prefs.PrefsHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        ru.diitcenter.optovik.App.appComponent.adresbookComponentBuilder()
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
        setContentView(ru.example.optovik.R.layout.activity_adresbook)

        initViews()

        call_to_admin.setOnClickListener {
            presenter.getAllLocations()
            adresbook_container.visibility = View.GONE
        }

        back_arrow_adresbook.setOnClickListener { finish() }

        presenter.getOperatopPhone()

        call_to_admin.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + operatorNumber))
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }
        }

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

    override fun showLocations(locations: List<ru.diitcenter.optovik.data.global.models.Location>) {
        val adapter = AdresbookAdapter(locations, prefsHelper)
        recycler_adresbook.adapter = adapter
        adapter.setOnAdresClickListener {
            adapter.notifyDataSetChanged()
            onBackPressed()
        }
    }

    override fun getOperatorPhone(operatorPhone: String) {
        operatorNumber = operatorPhone
        Log.e("OPERATOR","$operatorPhone")
    }

    override fun showError() {
        adresbook_container.visibility = View.VISIBLE
    }

    override fun onBackPressed() = finish()
}
