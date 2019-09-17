package ru.diitcenter.optovik.data.network

import io.reactivex.Single
import io.reactivex.annotations.Nullable
import retrofit2.http.*
import ru.diitcenter.optovik.data.global.models.*

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
    fun getOrderInfo(
        @Path("order_id") id: Int
    ): Single<ru.diitcenter.optovik.data.global.models.OrderInfo>

    @FormUrlEncoded
    @POST("/api/v1/login")
    fun getCode(
        @Field("telephone") telephone: String
    ): Single<Autorization>

    @FormUrlEncoded
    @POST("/api/v1/login")
    fun getToken(
        @Field("telephone") telephone: String,
        @Field("code") code: String
    ): Single<Autorization>

    @FormUrlEncoded
    @POST("/api/v1/user/token")
    fun setPushToken(
        @Field("token") token: String,
        @Field("device_id") device_id: String
    ): Single<PushToken>

    @POST("/api/v1/cart/{product_id}/add")
    fun addProductInBasket(
        @Path("product_id") productId: Int,
        @Query("count") count: Int
    ): Single<Product>

    @POST("/api/v1/cart/{product_id}/delete")
    fun deleteProductInBasket(
        @Path("product_id") productId: Int
    ): Single<Product>

    @POST("/api/v1/cart/{product_id}/set")
    fun changeProductInBasket(
        @Path("product_id") productId: Int,
        @Query("count") count: Int
    ): Single<Product>

    @GET("/api/v1/cart/clear")
    fun clearBasket(): Single<Basket>

    @FormUrlEncoded
    @POST("/api/v1/products")
    fun searchProducts(
        @Field("search") searchWord: String
    ): Single<Search>

    @FormUrlEncoded
    @POST("/api/v1/reviews/add")
    fun setFeedBack(
        @Field("order_id") orderId: Int,
        @Field("rating") rating: Int,
        @Field("review") review: String
    ): Single<FeedBack>

    @FormUrlEncoded
    @POST("/api/v1/cart/checkout")
    fun Checkout(
        @Nullable
        @Field("description") description: String,
        @Field("telephone") telephone: String
    ): Single<Checkout>


}