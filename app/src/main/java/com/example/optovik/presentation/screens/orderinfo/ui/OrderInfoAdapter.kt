package com.example.optovik.presentation.screens.orderinfo.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.optovik.R
import com.example.optovik.data.global.models.Basket
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.activity_order_info.view.*
import kotlinx.android.synthetic.main.item_order_info.view.*

private typealias OnProductClickListener = ((Basket) -> Unit)

class OrderInfoAdapter(private val products: List<Basket>) :
    RecyclerView.Adapter<OrderInfoAdapter.OrderInfoViewHolder>() {

    private var clickListener: OnProductClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderInfoViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_order_info, parent, false)
        return OrderInfoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: OrderInfoViewHolder, position: Int) =
        holder.bind(products[position], clickListener)

    override fun getItemCount(): Int = products.size

    fun setOnCategoryClickListener(listener: OnProductClickListener?) {
        clickListener = listener
    }

    class OrderInfoViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(products: Basket, clickListener: OnProductClickListener?) {
            Picasso.get()
                .load(products.product.image)
                .into(containerView.image_product_order_info)
            containerView.product_name_order_info.text = products.product.name
            containerView.price_order_info.text = products.product.price.toString()
            containerView.quantity_order_info.text = products.product.quantity.toString()
            itemView.setOnClickListener { clickListener?.invoke(products) }

        }
    }
}