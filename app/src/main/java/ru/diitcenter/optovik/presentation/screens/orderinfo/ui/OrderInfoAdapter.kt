package ru.diitcenter.optovik.presentation.screens.orderinfo.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_order_info.*
import kotlinx.android.synthetic.main.item_order_info.view.*
import kotlinx.android.synthetic.main.item_order_info.view.bottom_border_order_info
import ru.diitcenter.optovik.data.global.models.Basket
import ru.example.optovik.R

private  var sizeList = 0
private typealias OnProductClickListener = ((ru.diitcenter.optovik.data.global.models.Basket) -> Unit)

class OrderInfoAdapter(private val products: List<ru.diitcenter.optovik.data.global.models.Basket>) :
    RecyclerView.Adapter<OrderInfoAdapter.OrderInfoViewHolder>() {

    private var clickListener: OnProductClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderInfoViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_order_info, parent, false)
        sizeList = products.size
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
            containerView.price_order_info.text = "%,d".format(products.product.price)
            containerView.quantity_order_info.text = products.quantity.toString()
            itemView.setOnClickListener { clickListener?.invoke(products) }

            if(adapterPosition == sizeList-1)
                bottom_border_order_info.visibility = View.GONE

        }
    }
}