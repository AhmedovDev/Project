package com.example.optovik.data.basketholder

import com.example.optovik.data.global.models.Basket
import com.example.optovik.data.global.models.Products
import com.example.optovik.data.network.BasketApi
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit


public class BasketHolder {

    class Item(val products: Products, var quantity: Int)

    var basket: List<Basket> = ArrayList()

    var basketlist: MutableList<Basket> = ArrayList()

    var items: MutableList<Item> = ArrayList()

    fun addProduct(product: Products) {
            if (product.quantity == null)
                product.quantity = 0

            basketlist.add(Basket(product, product.quantity!! + 1))

        }



        fun deleteProduct(product: Products) {
            if (product.quantity == 0)
                product.quantity = null
            //  if(product.quantity != null && product.quantity != 0)
            basketlist.add(Basket(product, product.quantity!! - 1))

        }


//    fun addProduct(products: Products, quantity: Int) {
//        for (item in items) {
//            if (item.products.id == products.id) {
//                items[products.id].quantity = quantity
//            } else {
//                items.add(Item(products, quantity))
//            }
//        }
//    }

//    fun deleteProduct(products: Products) {
//        for (item in items) {
//            items.removeAt(item.products.id)
//        }
//    }

        fun reduceProductInBasket(products: Products, quantity: Int) {

            for (item in items) {
                if (item.products.id == products.id)
                    item.quantity = quantity
            }
        }

        fun updateBasket(basket: List<Basket>) {

        }

    }
