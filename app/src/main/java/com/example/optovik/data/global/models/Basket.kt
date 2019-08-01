package com.example.optovik.data.global.models


public class Basket  {

    class Item(val products: Products, var quantity: Int)

    var items: MutableList<Item> = ArrayList()

    fun addProduct(products: Products, quantity: Int) {
        for (item in items) {
            if (item.products.id == products.id) {
                items[products.id].quantity = quantity
            } else {
                items.add(Item(products, quantity))
            }
        }
    }

    fun deleteProduct(products: Products, quantity: Int) {
        for (item in items) {
            items.removeAt(item.products.id)
        }
    }

    fun reduceProductInBasket(products: Products, quantity: Int) {
        for (item in items) {
            if (item.products.id == products.id)
                item.quantity = quantity
        }
    }

    //todo добавить запрос на получение корзины
}
