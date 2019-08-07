package com.example.optovik.presentation.screens.basket.ui

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import com.example.optovik.R
import com.example.optovik.data.global.models.Basket
import com.example.optovik.data.global.models.Products
import com.example.optovik.presentation.global.utils.hideKeyboard
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_basket.*
import kotlinx.android.synthetic.main.item_basket.view.*
import kotlinx.android.synthetic.main.item_catalog.*
import kotlinx.android.synthetic.main.item_catalog.view.*

private typealias OnCategoryClickListener = ((Basket) -> Unit)

class BasketAdapter(private val basket: List<Basket>, private val clickListener1: (Basket) -> Unit) :
    RecyclerView.Adapter<BasketAdapter.BasketViewHolder>() {

    private var clickListener: OnCategoryClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_basket, parent, false)
        return BasketViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        holder.bind(basket[position].products,basket[position].quantity, clickListener!!)
        // todo сделать нормально
        holder.PlusClick(basket[position].products)
        holder.MinusClick(basket[position].products)
        holder.keyboardHide()
    }

    override fun getItemCount(): Int = basket.size

    fun setOnBasketClickListener(listener: OnCategoryClickListener?) {
        clickListener = listener
    }


    inner class BasketViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun PlusClick(products: Products) {
            var sum = 0
            with(containerView) {
                plus_basket.setOnClickListener {
                    if (input_product_basket.text.toString() == "")
                        input_product_basket.setText("0")
                    else{
                    sum = input_product_basket.text.toString().toInt()
                    minus_basket.visibility = View.VISIBLE
                    input_product_basket.visibility = View.VISIBLE
                    sum++
                    input_product_basket.setText("$sum")}
//                    clickListener1(products)
                }
            }
        }

        fun MinusClick(products: Products) {
            var sum = 0
            minus_basket.setOnClickListener {
                if (input_product_basket.text.toString() == "" || input_product_basket.text.toString() == "0") {
                    minus_basket.visibility = View.GONE
                } else {
                    minus_basket.isEnabled = true
                    sum = input_product_basket.text.toString().toInt()
                    sum--
                    if (sum == 0) {
                        minus_basket.visibility = View.GONE
                        input_product_basket.visibility = View.GONE
                    }
                    input_product_basket.setText("$sum")
                }
//                clickListener1(products)
            }
        }


        fun keyboardHide() {
            containerView.input_product_basket.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    hideKeyboard(containerView.context!!, containerView)
                }
                false
            }
        }


        @SuppressLint("ResourceAsColor")
        fun bind(products: Products,quantity: Int, clickListener: OnCategoryClickListener) {
            Picasso.get()
                .load(products.image)
                .into(containerView.image_product_basket)
            containerView.product_name_basket.text = products.name
            containerView.price_basket.text = products.price.toString()
            containerView.count_product_basket.text = products.count
            containerView.input_product_basket.setText("$quantity")
            var isEstimatedPrice = products.isEstimatedPrice
            if (isEstimatedPrice == true) containerView.isEstimatedPrise_basket.visibility = View.VISIBLE

            if (input_product_basket.text.toString() == "") minus.visibility = View.GONE

//            containerView.image_product.setOnClickListener { clickListener.invoke(products) }
//            containerView.product_name.setOnClickListener { clickListener.invoke(products) }
//            containerView.price_and_count.setOnClickListener { clickListener.invoke(products) }
        }
    }
}