package ru.diitcenter.optovik.data.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query
import ru.diitcenter.optovik.data.global.models.MainModel

interface MainApi {

    @GET("/api/v1/index/")
    fun getCategory(
    ): Single<MainModel>

    @GET("/api/v1/products")
    fun getDataCatalog(
        @Query("category_id") id: Int
    ): Single<ru.diitcenter.optovik.data.global.models.Catalog>
    //  /VladimirHoldar/OptovikCatalog/master/db.json

    @GET("/api/v1/products/{product_id}")
    fun getProductCard(
        @Path("product_id") id: Int
    ): Single<ru.diitcenter.optovik.data.global.models.ProductCard>

    @GET("/api/v1/cart")
    fun getDeliveryAndBasket(
    ): Single<ru.diitcenter.optovik.data.global.models.DeliveryAndBasket>

    @GET("/api/v1/companies")
    fun getLocation(): Single<List<ru.diitcenter.optovik.data.global.models.Location>>

    @GET("/api/v1/orders")
    fun getMyOrder(): Single<List<ru.diitcenter.optovik.data.global.models.MyOrder>>

    @GET("/api/v1/orders/{order_id}")
    fun getOrderInfo(@Path("order_id") id: Int
    ): Single<ru.diitcenter.optovik.data.global.models.OrderInfo>
}