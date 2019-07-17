package com.example.optovik.presentation.global

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import ru.terrakok.cicerone.Router

open class BasePresenter<T : MvpView>(private val router: Router) : MvpPresenter<T>() {

    protected val subscriptions = CompositeDisposable()

    override fun onDestroy() {
        subscriptions.dispose()
    }

    protected fun Disposable.connect() = subscriptions.add(this)


    open fun onBackPressed() {
        router.exit()
    }
}
