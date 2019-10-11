package ru.diitcenter.optovik.data.global

import io.reactivex.Single
import retrofit2.http.Field
import ru.diitcenter.optovik.data.global.models.*

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

    fun setPushToken(token: String, deviceId: String): Single<PushToken>

    fun searchProduct(searchWord: String): Single<Search>

    fun checkOut(description: String, telephone: String): Single<Checkout>

    fun setFeedback(orderId: Int, rating: Int, review: String): Single<FeedBack>

    fun addProductInBasket(productId: Int, count: Int): Single<Product>

    fun deleteProductInBasket(productId: Int): Single<Product>

    fun changeProductInBasket(productId: Int, count: Int): Single<Product>

    fun getOperatorPhone (): Single<OperatorPhone>

}