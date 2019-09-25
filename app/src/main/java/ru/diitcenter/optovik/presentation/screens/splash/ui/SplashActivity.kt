package ru.diitcenter.optovik.presentation.screens.splash.ui

import android.os.Bundle
import android.content.Intent
import android.util.Base64
import android.util.Log
import android.view.ViewDebug
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.google.firebase.iid.FirebaseInstanceId
import ru.diitcenter.optovik.data.basketholder.BasketHolder
import ru.diitcenter.optovik.data.global.models.Basket
import ru.diitcenter.optovik.data.prefs.PrefsHelper
import ru.diitcenter.optovik.presentation.screens.autorization.ui.AutorizationActivity
import ru.diitcenter.optovik.presentation.screens.main.ui.MainActivity
import ru.diitcenter.optovik.presentation.screens.notofication.ui.NotificationActivity
import ru.diitcenter.optovik.presentation.screens.orderinfo.ui.OrderInfoActivity
import ru.diitcenter.optovik.presentation.screens.splash.mvp.SplashPresenter
import ru.diitcenter.optovik.presentation.screens.splash.mvp.SplashView
import ru.example.optovik.R
import javax.inject.Inject


class SplashActivity : MvpAppCompatActivity(), SplashView {

    override fun goToAutorization() {
        val intent = Intent(this, AutorizationActivity::class.java)
        startActivity(intent)
        finish()
    }

    @Inject
    @InjectPresenter
    lateinit var presenter: SplashPresenter

    @Inject
    lateinit var prefsHelper: PrefsHelper

    @Inject
    lateinit var basketHelper: BasketHolder

    @ProvidePresenter
    fun providePresenter() = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        ru.diitcenter.optovik.App.appComponent.splashComponentBuilder()
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        intent.extras?.let {
            val pushType = it.getString("type", "")
            val targetId = it.getString("targetId", "")

            if(pushType == "mailing"){
                val intent = Intent(this, NotificationActivity::class.java)
                startActivity(intent)}
            if(pushType == "stock"){
                // todo реализовать получение имени категории
                intent.putExtra("nameCategory", "${pushType}")
                intent.putExtra("category_id", targetId )
                startActivity(intent)
                startActivity(intent)
            }
            if(pushType == "status"){
                val intent = Intent(this, OrderInfoActivity::class.java)
                intent.putExtra("order_id", targetId)
                startActivity(intent)
            }

            Log.e("PUSH_TYPE - ", pushType)
            Log.e("PUSH_TARGET"," - $targetId")
        }

        val token = FirebaseInstanceId.getInstance().getToken()
        Log.d("TOKEN_PUSH", "$token")


            presenter.setPushToken(prefsHelper.getToken().toString(),token.toString())



        Log.e("TOKEN_USER","${prefsHelper.getToken()}")

        basketHelper.synchronizeBasketWithServer()

    }

    override fun goToMain() {
                   val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()

    }

    // todo добить
    override fun getBasket(basket: List<Basket>) {
        if (basket.isNotEmpty()) {
            basketHelper.items = basket.map {
                ru.diitcenter.optovik.data.basketholder.BasketHolder.Item(
                    it.product,
                    it.quantity
                )
            } as MutableList
        }
   }

    override fun showError() {
        Toast.makeText(this, "Проблемы с интернетом", Toast.LENGTH_SHORT).show()
    }


}
