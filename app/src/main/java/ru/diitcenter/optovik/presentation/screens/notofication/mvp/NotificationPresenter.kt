package ru.diitcenter.optovik.presentation.screens.notofication.mvp

import android.annotation.TargetApi
import android.os.Build
import android.support.annotation.RequiresApi
import com.arellomobile.mvp.InjectViewState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import ru.diitcenter.optovik.presentation.global.Screens
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.Screen
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject


@InjectViewState
class NotificationPresenter @Inject constructor(
    private val router: Router,
    private val dataManager: ru.diitcenter.optovik.data.global.DataManager,
    private var basketHolder: ru.diitcenter.optovik.data.basketholder.BasketHolder
) : ru.diitcenter.optovik.presentation.global.BasePresenter<NotificationView>(router, dataManager) {


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        this.getNotifications()
    }

    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(Build.VERSION_CODES.O)
    fun getNotifications() {
        subscriptions += dataManager.getNotification()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnSubscribe { viewState.showProgress(true) }
            .doAfterTerminate { viewState.showProgress(false) }
            .subscribe(
                { data ->
                    data.forEach {
                            measurement -> measurement.date?.let {
                        measurement.date = toMeasurementDate(it)
                        viewState.showNotification(data)
                    }}
                },
                {
                    viewState.showError()
                }
            )
    }

    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(Build.VERSION_CODES.O)
    fun toMeasurementDate(date: String): String {
        val accessor = DateTimeFormatter.ofPattern("dd.MM.yyyy").parse(date)
        val localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy"))
        return when (LocalDate.now().dayOfYear - localDate.dayOfYear) {
            0 -> "Сегодня"
            1 -> "Вчера"
            else -> DateTimeFormatter.ofPattern("dd.MM.yyyy").format(accessor)
        }
    }

    fun goToProductCard(productId: Int) {
        router.navigateTo(Screens.ProductCard(productId))
    }
}