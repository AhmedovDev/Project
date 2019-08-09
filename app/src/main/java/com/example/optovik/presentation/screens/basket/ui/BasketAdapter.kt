package com.example.optovik.presentation.screens.basket.ui

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import com.example.optovik.R
import com.example.optovik.data.basketholder.BasketHolder
import com.example.optovik.data.global.models.Product
import com.example.optovik.presentation.global.utils.hideKeyboard
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_basket.*
import kotlinx.android.synthetic.main.item_basket.view.*
import kotlinx.android.synthetic.main.item_catalog.*
import kotlinx.android.synthetic.main.activity_catalog.product as product1

private typealias OnCategoryClickListener = ((Product) -> Unit)

class BasketAdapter(
    private val clickListenerPlus: (Product) -> Unit,
    private val clickListenerMinus: (Product) -> Unit,
    private val basketholder: BasketHolder,
    private val clickListenerdrop: (Product) -> Unit
) :
    RecyclerView.Adapter<BasketAdapter.BasketViewHolder>() {

    private var clickListener: OnCategoryClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_basket, parent, false)
        return BasketViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        holder.bind(
            basketholder.items[position].product,
            basketholder.items[position].quantity,
            clickListener!!
        )
        // todo сделать нормально
        holder.plusClickBasket(basketholder.items[position].product)
        holder.minusClickBasket(basketholder.items[position].product, position)
        holder.dropClick(basketholder.items[position].product)
        holder.keyboardHide()

    }

    override fun getItemCount(): Int = basketholder.items.size

    fun setOnBasketClickListener(listener: OnCategoryClickListener?) {
        clickListener = listener
    }

    inner class BasketViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun plusClickBasket(product: Product) {
            var sum = 0
            with(containerView) {
                plus_basket.setOnClickListener {
                    if (input_product_basket.text.toString() == "")
                        input_product_basket.setText("0")
                    else {
                        sum = input_product_basket.text.toString().toInt()
                        minus_basket.visibility = View.VISIBLE
                        input_product_basket.visibility = View.VISIBLE
                        sum++
                        input_product_basket.setText("$sum")
                    }
                    clickListenerPlus(product)
                }
            }
        }

        fun minusClickBasket(product: Product, position: Int) {
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
                        notifyItemRemoved(adapterPosition)
                    }
                    input_product_basket.setText("$sum")
                }
                clickListenerMinus(product)

            }

        }


        fun dropClick(product: Product) {
            drop.setOnClickListener {
                clickListenerdrop(product)
                notifyItemRemoved(adapterPosition)
                basketholder.items
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
        fun bind(product: Product, quantity: Int, clickListener: OnCategoryClickListener) {
            basketholder.items.forEach { item ->
                Picasso.get()
                    .load(item.product.image)
                    .into(containerView.image_product_basket)
                containerView.product_name_basket.text = item.product.name
                containerView.price_basket.text = item.product.price.toString()
                containerView.count_product_basket.text = item.product.count
                containerView.input_product_basket.setText("$quantity")
                if (input_product_basket.text.toString() != "") {
                    input_product_basket.visibility = View.VISIBLE
                    minus_basket.visibility = View.VISIBLE
                }
                var isEstimatedPrice = item.product.isEstimatedPrice
                if (isEstimatedPrice == true) containerView.isEstimatedPrise_basket.visibility = View.VISIBLE

                if (input_product_basket.text.toString() == "") minus.visibility = View.GONE
            }
//            containerView.image_product_basket.setOnClickListener { clickListener.invoke(product) }
//            containerView.product_name_basket.setOnClickListener { clickListener.invoke(product) }
//            containerView.price_and_count.setOnClickListener { clickListener.invoke(product) }
            itemView.setOnClickListener { clickListener.invoke(product) }
        }
    }
}
