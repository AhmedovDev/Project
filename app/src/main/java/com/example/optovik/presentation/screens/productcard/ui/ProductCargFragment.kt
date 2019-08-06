package com.example.optovik.presentation.screens.productcard.ui


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.optovik.App

import com.example.optovik.R
import com.example.optovik.data.basketholder.BasketHolder
import com.example.optovik.data.global.models.ProductCard
import com.example.optovik.presentation.global.BaseFragment
import com.example.optovik.presentation.global.utils.hideKeyboard
import com.example.optovik.presentation.global.utils.showKeyboard
import com.example.optovik.presentation.screens.main.ui.CirclePagerIndicatorDecoration
import com.example.optovik.presentation.screens.productcard.mvp.ProductCardPresenter
import com.example.optovik.presentation.screens.productcard.mvp.ProductCardView
import kotlinx.android.synthetic.main.fragment_input_phone2.*
import kotlinx.android.synthetic.main.fragment_product_carg.*
import kotlinx.android.synthetic.main.toolbar_product_card.*
import javax.inject.Inject


class ProductCargFragment : BaseFragment(), ProductCardView {


    @Inject
    @InjectPresenter
    lateinit var presenter: ProductCardPresenter

    @Inject
    lateinit var basketHolder: BasketHolder

    private var productCard: ProductCard? = null


    @ProvidePresenter
    fun providePresenter() = presenter


    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.productCardComponentBuilder()
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_carg, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()


        // скрыть клаву при нажатии на физическую кнопку "назад"
        input_product.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                hideKeyboard(context!!, view)
            }
            false
        }

        plusClick()
        minusClick()

        back_arrow_product_card.setOnClickListener { presenter.gotoback() }

    }

    private fun initViews() {

        recycler_images.run {
            layoutManager = LinearLayoutManager(recycler_images.context, LinearLayoutManager.HORIZONTAL, false)
            val snapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(recycler_images)
            addItemDecoration(
                CirclePagerIndicatorDecoration()
            )
        }
        updateClick()

    }

    fun plusClick() {
        var sum = 0

        plus.setOnClickListener {
            if (input_product.text.toString() == "")
                input_product.setText("0")
            sum = input_product.text.toString().toInt()
            minus.visibility = View.VISIBLE
            input_product.visibility = View.VISIBLE
            sum++
            input_product.setText("$sum")


        }
    }


    fun minusClick() {
        var sum = 0
        minus.setOnClickListener {
            if (input_product.text.toString() == "" || input_product.text.toString() == "0") {
                minus.visibility = View.GONE
            } else {
                minus.isEnabled = true
                sum = input_product.text.toString().toInt()
                sum--
                if (sum == 0) {
                    minus.visibility = View.GONE
                    input_product.visibility = View.GONE
                }
                input_product.setText("$sum")
            }


        }
    }


    fun updateClick() {
        update_productcard.setOnClickListener { presenter.getAllData() }
    }

    override fun showProgress(progress: Boolean) {
        if (progress) {
            progressBar_productcard.visibility = View.VISIBLE
            nestedScrollView_productCard.visibility = View.GONE
        } else {
            progressBar_productcard.visibility = View.GONE
            nestedScrollView_productCard.visibility = View.VISIBLE
        }

    }

    override fun showError() {
        product_card_container.visibility = View.VISIBLE
        nestedScrollView_productCard.visibility = View.GONE
        back_arrow_product_card.visibility = View.GONE
    }

    override fun visiblProductCard() {
        product_card_container.visibility = View.GONE
        nestedScrollView_productCard.visibility = View.VISIBLE
        back_arrow_product_card.visibility = View.VISIBLE
    }

    override fun showProductCardImages(productCard: ProductCard) {
        val adapter = ProductCardAdapter(productCard.images)
        recycler_images.adapter = adapter
    }

    override fun showProductCardInformation(productCard: ProductCard) {
        title.text = productCard.title
        price.text = productCard.price.toString()
        count_product.text = productCard.count
        discription.text = productCard.description
    }

    override fun onBackPressed() = presenter.gotoback()

}
