package com.example.optovik.presentation.screens.checkorder.mvp

import com.arellomobile.mvp.InjectViewState
import com.example.optovik.data.basketholder.BasketHolder
import com.example.optovik.data.global.DataManager
import com.example.optovik.presentation.global.BasePresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject


@InjectViewState
class CheckOrderPresenter @Inject constructor(
    private val router: Router,
    private val dataManager: DataManager,
    private val basketHolder: BasketHolder
) : BasePresenter<CheckOrderView>(router, dataManager){

}