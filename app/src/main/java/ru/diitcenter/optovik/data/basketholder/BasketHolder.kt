//package com.diitcenter.optovik.data.basketholder
//
//import android.util.ArrayMap
//import com.diitcenter.optovik.data.global.models.Basket
//import com.diitcenter.optovik.data.global.models.Product
//import java.util.*
//import kotlin.collections.ArrayList
//
//interface BasketListener {
//    fun onUpdateBasketItems(items: ArrayMap<Int,BasketHolder.Item>)
//}
//
//class BasketHolder {
//
//    class Item(val product: Product, var quantity: Int)
//
//    var items: ArrayMap<Int,Item> = ArrayMap()
//    var listeners = ArrayList<BasketListener>()
//
//
//    fun addProduct(product: Product) {
//
//        items.put(product.id, Item(product,+1))
//        basketUpdated()
//    }
//
//    fun deleteProduct(product: Product) {
//
//        if()
//        items.setValueAt(product.id,Item(product,-1))
//
//        basketUpdated()
//    }
//
//    fun dropProduct(product: Product) {
//
//        val haveItem = items.filter {
//            it.product.id == product.id
//        }.firstOrNull() ?: return
//
//        items.remove(haveItem)
//        basketUpdated()
//    }
//
//    fun reduceProductInBasket(product: Product, quantity: Int) {
//
//        for (item in items) {
//            if (item.product.id == product.id)
//                item.quantity = quantity
//        }
//    }
//
//    fun updateBasket(basket: List<Basket>) {
//
//    }
//
//    private fun basketUpdated() {
//        listeners.forEach {
//            it.onUpdateBasketItems(items)
//        }
//    }
//
//    fun subscribe(listener: BasketListener) {
//        listeners.add(listener)
//    }
//
//
//}







package ru.diitcenter.optovik.data.basketholder

interface BasketListener {
    fun onUpdateBasketItems(items: MutableList<ru.diitcenter.optovik.data.basketholder.BasketHolder.Item>)
}

class BasketHolder {

    class Item(val product: ru.diitcenter.optovik.data.global.models.Product, var quantity: Int)

    var items: MutableList<ru.diitcenter.optovik.data.basketholder.BasketHolder.Item> = ArrayList()
    var listeners = ArrayList<ru.diitcenter.optovik.data.basketholder.BasketListener>()

    fun addProduct(product: ru.diitcenter.optovik.data.global.models.Product) {

        val haveItem = items.filter {
            it.product.id == product.id
        }.firstOrNull()

        if (haveItem != null) {
            haveItem.quantity += 1
        } else
            items.add(ru.diitcenter.optovik.data.basketholder.BasketHolder.Item(product, 1))

        basketUpdated()
    }

    fun deleteProduct(product: ru.diitcenter.optovik.data.global.models.Product) {

        val haveItem = items.filter {
            it.product.id == product.id
        }.firstOrNull() ?: return

        if (haveItem.quantity != 0)
            haveItem.quantity -= 1

        if (haveItem.quantity == 0 || haveItem.quantity == null)
            items.remove(haveItem)

        basketUpdated()
    }

    fun dropProduct(product: ru.diitcenter.optovik.data.global.models.Product) {

        val haveItem = items.filter {
            it.product.id == product.id
        }.firstOrNull() ?: return

        items.remove(haveItem)
        basketUpdated()
    }

    fun reduceProductInBasket(product: ru.diitcenter.optovik.data.global.models.Product, quantity: Int) {

        for (item in items) {
            if (item.product.id == product.id)
                item.quantity = quantity
        }
    }

    fun updateBasket(basket: List<ru.diitcenter.optovik.data.global.models.Basket>) {

    }

    private fun basketUpdated() {
        listeners.forEach {
            it.onUpdateBasketItems(items)
        }
    }

    fun subscribe(listener: ru.diitcenter.optovik.data.basketholder.BasketListener) {
        listeners.add(listener)
    }


}


