package com.example.optovik.data.basketholder

import com.example.optovik.data.global.models.Basket
import com.example.optovik.data.global.models.Product

interface BasketListener {
    fun onUpdateBasketItems(items: MutableList<BasketHolder.Item>)
}

class BasketHolder {

    class Item(val product: Product, var quantity: Int)

    var items: MutableList<Item> = ArrayList()
    var listeners = ArrayList<BasketListener>()

    fun addProduct(product: Product) {

        val haveItem = items.filter {
            it.product.id == product.id
        }.firstOrNull()

        if (haveItem != null) {
            haveItem.quantity += 1
        } else
            items.add(Item(product, 1))

        basketUpdated()
    }

    fun deleteProduct(product: Product) {

        val haveItem = items.filter {
            it.product.id == product.id
        }.firstOrNull() ?: return

        if (haveItem.quantity != 0)
            haveItem.quantity -= 1

        if (haveItem.quantity == 0 || haveItem.quantity == null)
            items.remove(haveItem)

        basketUpdated()
    }

    fun dropProduct(product: Product) {

        val haveItem = items.filter {
            it.product.id == product.id
        }.firstOrNull() ?: return

        items.remove(haveItem)
        basketUpdated()
    }

    fun reduceProductInBasket(product: Product, quantity: Int) {

        for (item in items) {
            if (item.product.id == product.id)
                item.quantity = quantity
        }
    }

    fun updateBasket(basket: List<Basket>) {

    }

    private fun basketUpdated() {
        listeners.forEach {
            it.onUpdateBasketItems(items)
        }
    }

    fun subscribe(listener: BasketListener) {
        listeners.add(listener)
    }


}
