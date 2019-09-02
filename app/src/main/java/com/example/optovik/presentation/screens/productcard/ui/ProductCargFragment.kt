package com.example.optovik.presentation.screens.productcard.ui


import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.optovik.App
import com.example.optovik.data.basketholder.BasketHolder
import com.example.optovik.data.global.models.Product
import com.example.optovik.data.global.models.ProductCard
import com.example.optovik.presentation.global.BaseFragment
import com.example.optovik.presentation.global.utils.hideKeyboard
import com.example.optovik.presentation.global.utils.withArgs
import com.example.optovik.presentation.screens.basket.ui.BasketActivity
import com.example.optovik.presentation.screens.main.ui.CirclePagerIndicatorDecoration
import com.example.optovik.presentation.screens.productcard.mvp.ProductCardPresenter
import com.example.optovik.presentation.screens.productcard.mvp.ProductCardView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_product_carg.*
import kotlinx.android.synthetic.main.toolbar_product_card.*
import javax.inject.Inject


class ProductCargFragment : BaseFragment(), ProductCardView {


    @Inject
    @InjectPresenter
    lateinit var presenter: ProductCardPresenter

    @Inject
    lateinit var basket: BasketHolder

    @ProvidePresenter
    fun providePresenter() = presenter

    lateinit var product: Product


    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.productCardComponentBuilder()
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
        arguments?.run {
            product = getParcelable(PRODUCT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(
            com.example.optovik.R.layout.fragment_product_carg,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateBasketButton()
        initViews()
        // скрыть клаву при нажатии на физическую кнопку "назад"
        input_product.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                hideKeyboard(context!!, view)
            }
            false
        }

        back_arrow_product_card.setOnClickListener { presenter.gotoback() }
        button_green.setOnClickListener {
            startActivity(Intent(activity, BasketActivity::class.java))
        }
        plusClick(product)
        minusClick(product)

    }

    companion object {

        fun newInstance(product: Product) = ProductCargFragment().withArgs {
            putParcelable(PRODUCT, product)
        }

        private const val PRODUCT = "product"
    }

    private fun initViews() {

        recycler_images.run {
            layoutManager =
                LinearLayoutManager(recycler_images.context, LinearLayoutManager.HORIZONTAL, false)
            val snapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(recycler_images)
            addItemDecoration(
                CirclePagerIndicatorDecoration()
            )
        }
        updateClick()
    }

    private fun updateBasketButton() {
        var priceAll: Int = 0
        basket.items.forEach { item ->
            priceAll += (item.product.price * item.quantity)
        }
        val haveItem = basket.items.filter {
            it.product.isEstimatedPrice == true
        }.firstOrNull()
        if (haveItem == null)
            isEstimatedPrise_product_card.visibility = View.GONE
        else
            isEstimatedPrise_product_card.visibility = View.VISIBLE
        price_on_button_product_card.setText("$priceAll")
        count_on_button_product_card.setText("${basket.items.size}")
    }

    //
    fun plusClick(product: Product) {

        if (product.quantity != null) {
            input_product.setText("${product.quantity}")
            input_product.visibility = View.VISIBLE
            minus.visibility = View.VISIBLE
        }

        var sum = 0

        plus.setOnClickListener {
            if (input_product.text.toString() == "" || input_product.text.toString() == null)
                input_product.setText("0")
            sum = input_product.text.toString().toInt()
            minus.visibility = View.VISIBLE
            input_product.visibility = View.VISIBLE
            sum++
            input_product.setText("$sum")
            basket.addProduct(product)
            updateBasketButton()

        }
    }

    fun minusClick(product: Product) {


        if (product.quantity != null || product.quantity != 0) {
            input_product.setText("${product.quantity}")
            input_product.visibility = View.VISIBLE
            minus.visibility = View.VISIBLE
        }
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
                basket.deleteProduct(product)
                updateBasketButton()
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

    override fun onResume() {
        super.onResume()
        presenter.getAllData()
        updateBasketButton()
        Log.e("aaaResume", "aaaResume")
        val haveItem = basket.items.filter {
            it.product.id == product.id
        }.firstOrNull()
        if (haveItem != null) {
            input_product.setText("${haveItem.quantity}")
            input_product.visibility = View.VISIBLE
            minus.visibility = View.VISIBLE
        } else input_product.setText("0")
        input_product.visibility = View.GONE
        minus.visibility = View.GONE
    }


    override fun onStart() {
        super.onStart()
        updateBasketButton()
        Log.e("aaaStart", "aaaStart")
    }

    //Предовать в метод продукт
    override fun showProductCardInformation(productCard: ProductCard) {

        val haveItem = basket.items.filter {
            it.product.id == product.id
        }.firstOrNull()
        if (haveItem != null) {
            input_product.setText("${haveItem.quantity}")
            input_product.visibility = View.VISIBLE
            minus.visibility = View.VISIBLE
        }

        name_product_card.text = productCard.title
        title.text = productCard.title
        price.text = productCard.price.toString()
        count_product.text = productCard.count
        discription.text = productCard.description

    }

    override fun onBackPressed() = presenter.gotoback()

}
