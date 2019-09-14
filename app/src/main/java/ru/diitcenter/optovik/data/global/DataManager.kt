package ru.diitcenter.optovik.data.global

import io.reactivex.Single
import ru.diitcenter.optovik.data.global.models.Autorization
import ru.diitcenter.optovik.data.global.models.Basket

interface DataManager {
    fun getMain(): Single<ru.diitcenter.optovik.data.global.models.MainModel>

    fun getDataCatalog(id: Int): Single<ru.diitcenter.optovik.data.global.models.Catalog>

    fun getProductCard(id: Int): Single<ru.diitcenter.optovik.data.global.models.ProductCard>

    fun getBasket(): Single<ru.diitcenter.optovik.data.global.models.DeliveryAndBasket>

    fun getLocation(): Single<List<ru.diitcenter.optovik.data.global.models.Location>>

    fun getMyOrder(): Single<List<ru.diitcenter.optovik.data.global.models.MyOrder>>

    fun getNotification(): Single<List<ru.diitcenter.optovik.data.global.models.Notification>>

    fun getOrderInfo(id: Int): Single<ru.diitcenter.optovik.data.global.models.OrderInfo>

    fun clearBasket(): Single<Basket>

    fun getCode(telephone: String): Single<Autorization>

    fun getToken(telephone: String, code: String): Single<Autorization>

}