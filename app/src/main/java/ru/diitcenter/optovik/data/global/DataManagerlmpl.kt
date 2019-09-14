package ru.diitcenter.optovik.data.global


import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import ru.diitcenter.optovik.data.global.models.Autorization
import ru.diitcenter.optovik.data.global.models.Basket
import ru.diitcenter.optovik.data.global.models.Catalog
import ru.diitcenter.optovik.data.global.models.ProductCard
import ru.diitcenter.optovik.data.prefs.PrefsHelper
import javax.inject.Inject
import javax.inject.Named

class DataManagerlmpl @Inject constructor(
    @Named("API_OPTOVIK") val api: ru.diitcenter.optovik.data.network.MainApi,
    @Named("API_NOTIFICATION") val apiNotification: ru.diitcenter.optovik.data.network.NotificationApi,
    private val prefsHelper: PrefsHelper

) : DataManager {
    override fun getCode(telephone : String): Single<Autorization> =
        api.getCode(telephone)
            .subscribeOn(Schedulers.io())


    override fun getToken(telephone: String, code: String): Single<Autorization> =
        api.getToken(telephone,code)
            .subscribeOn(Schedulers.io())

    override fun clearBasket(): Single<Basket> =
        api.clearBasket()
            .subscribeOn(Schedulers.io())

    override fun getProductCard(id: Int): Single<ProductCard> =
        api.getProductCard(id)
            .subscribeOn(Schedulers.io())

    override fun getDataCatalog(id: Int): Single<Catalog> =
        api.getDataCatalog( id)
            .subscribeOn(Schedulers.io())

    override fun getOrderInfo(id: Int): Single<ru.diitcenter.optovik.data.global.models.OrderInfo> =
        api.getOrderInfo(id)
            .subscribeOn(Schedulers.io())

    override fun getNotification(): Single<List<ru.diitcenter.optovik.data.global.models.Notification>> =
        apiNotification.getNotification()
            .subscribeOn(Schedulers.io())

    override fun getMyOrder(): Single<List<ru.diitcenter.optovik.data.global.models.MyOrder>> =
        api.getMyOrder()
            .subscribeOn(Schedulers.io())

    override fun getLocation(): Single<List<ru.diitcenter.optovik.data.global.models.Location>> =
        api.getLocation()
            .subscribeOn(Schedulers.io())


    override fun getBasket(): Single<ru.diitcenter.optovik.data.global.models.DeliveryAndBasket> =
        api.getDeliveryAndBasket()
            .subscribeOn(Schedulers.io())

    override fun getMain(): Single<ru.diitcenter.optovik.data.global.models.MainModel> =
        api.getCategory()
            .subscribeOn(Schedulers.io())


}
