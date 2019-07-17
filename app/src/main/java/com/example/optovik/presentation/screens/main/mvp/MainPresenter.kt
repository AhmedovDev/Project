package com.example.optovik.presentation.screens.main.mvp

import com.arellomobile.mvp.InjectViewState
import com.example.optovik.data.global.DataManager
import com.example.optovik.presentation.global.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class  MainPresenter @Inject constructor(private val router: Router, private val dataManager: DataManager
) : BasePresenter<MainView>(router)  {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        getEvents()
        getAllCategories()
    }

 fun getAllCategories() {
        dataManager.getUsers()
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { viewState.showProgress(true) }
            .doAfterTerminate { viewState.showProgress(false) }
            .subscribe(
                { users ->
                    val list = users
                    viewState.showCategories(users) },
                {
                    viewState.showError("Данные не пришли")
                }
            )
            .connect()
    }

    fun getEvents() {
        dataManager.getUsers()
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { viewState.showProgress(true) }
            .doAfterTerminate { viewState.showProgress(false) }
            .subscribe(
                { users ->
                    val list = users
                    viewState.showEvents(users) },
                {
                    viewState.showError("Данные не пришли")
                }
            )
            .connect()
    }

    fun retryCheck() {
        getAllCategories()
        getEvents()
    }
}