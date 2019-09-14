package ru.diitcenter.optovik.data.basketholder

interface BasketListener {
    fun onUpdateBasketItems(items: MutableList<ru.diitcenter.optovik.data.basketholder.BasketHolder.Item>)
}

class BasketHolder {

    class Item(val product: ru.diitcenter.optovik.data.global.models.Product, var quantity: Int)

    var items: MutableList<Item> = ArrayList()
    var listeners = ArrayList<BasketListener>()

    fun addProduct(product: ru.diitcenter.optovik.data.global.models.Product) {

        val haveItem = items.filter {
            it.product.id == product.id
        }.firstOrNull()

        if (haveItem != null) {
            haveItem.quantity += 1
        } else
            items.add(Item(product, 1))

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


