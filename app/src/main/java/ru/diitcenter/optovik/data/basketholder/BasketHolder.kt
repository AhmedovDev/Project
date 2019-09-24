package ru.diitcenter.optovik.data.basketholder

import com.arellomobile.mvp.InjectViewState
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import ru.diitcenter.optovik.data.global.DataManager
import ru.diitcenter.optovik.data.global.DataManagerlmpl
import javax.inject.Inject

interface BasketListener {
    fun onUpdateBasketItems(items: MutableList<BasketHolder.Item>)
}

class BasketHolder @Inject constructor(private val dataManager: DataManager) {

    class Item(val product: ru.diitcenter.optovik.data.global.models.Product, var quantity: Int)

    var items: MutableList<Item> = ArrayList()
    var listeners = ArrayList<BasketListener>()

    fun addProduct(
        product: ru.diitcenter.optovik.data.global.models.Product,
        completion: (Boolean) -> Unit
    ) {

        val haveItem = items.filter {
            it.product.id == product.id
        }.firstOrNull()

        if (haveItem != null) {
            subscriptions += dataManager.changeProductInBasket(product.id, haveItem.quantity + 1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    haveItem.quantity += 1
                    basketUpdated()
                    completion(false)
                }, {
                    completion(true)
                })
        } else {
            subscriptions += dataManager.addProductInBasket(product.id, 1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    items.add(Item(product, 1))
                    basketUpdated()
                    completion(false)
                }, {
                    completion(true)
                })
        }
    }

    fun deleteProduct(
        product: ru.diitcenter.optovik.data.global.models.Product,
        completion: (Boolean) -> Unit
    ) {

        val haveItem = items.filter {
            it.product.id == product.id
        }.firstOrNull()


        if (haveItem != null) {
            if (haveItem.quantity != 1) {
                subscriptions += dataManager.changeProductInBasket(
                    product.id,
                    haveItem.quantity - 1
                )
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe({
                        haveItem.quantity -= 1
                        basketUpdated()
                        completion(false)
                    }, {
                        completion(true)
                    })

            } else {
                subscriptions += dataManager.deleteProductInBasket(product.id)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe({
                        items.removeAll { it.product.id == product.id }
                        completion(false)
                        basketUpdated()
                    }, {
                        completion(true)
                    })
            }
        }
    }

    fun dropProduct(product: ru.diitcenter.optovik.data.global.models.Product,
                    completion: (Boolean) -> Unit
    ) {
        subscriptions += dataManager.deleteProductInBasket(product.id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                completion(false)
            }, {
                completion(true)
            })


    }


    private fun basketUpdated() {
        listeners.forEach {
            it.onUpdateBasketItems(items)
        }
    }

    fun subscribe(listener: BasketListener) {
        listeners.add(listener)
    }

    protected val subscriptions = CompositeDisposable()

    fun unSubscribe(listener: BasketListener) {

        subscriptions.dispose()
    }

//    private val disposeBag = Dispo

    protected fun Disposable.connect() = subscriptions.add(this)

    fun synchronizeBasketWithServer() {
        subscriptions += dataManager.getBasket()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                { data ->
                    data.basket?.let {
                        items = it.map {
                            ru.diitcenter.optovik.data.basketholder.BasketHolder.Item(
                                it.product,
                                it.quantity
                            )
                        } as MutableList
                    }
                },
                { error ->

                    when ((error as HttpException).code()) {
                        401 -> {
                            var a = 1

                        }
                        else -> {
                            var a = 1
                        }
                    }
                }
            )
    }


}


