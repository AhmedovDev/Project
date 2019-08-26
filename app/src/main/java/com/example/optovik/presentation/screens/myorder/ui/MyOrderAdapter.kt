package com.example.optovik.presentation.screens.myorder.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.optovik.data.global.models.MyOrder
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_my_order.*
import kotlinx.android.synthetic.main.item_my_order.view.*

private typealias OnAdresClickListener = ((MyOrder) -> Unit)


class MyOrderAdapter(private val myOrder: List<MyOrder>) :
    RecyclerView.Adapter<MyOrderAdapter.MyOrderViewHolder>() {

    private var clickListener: OnAdresClickListener? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyOrderViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(com.example.optovik.R.layout.item_my_order, parent, false)

        return MyOrderViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyOrderViewHolder, position: Int) {
        holder.bind(myOrder[position], clickListener)
    }


    override fun getItemCount(): Int = myOrder.size

    fun setOnAdresClickListener(listener: OnAdresClickListener?) {
        clickListener = listener
    }


    inner class MyOrderViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {


        fun bind(
            myOrder: MyOrder,
            clickListener: OnAdresClickListener?
        ) {
            containerView.date.text = myOrder.date
            containerView.sum.text = myOrder.sum.toString()
            containerView.title.text = myOrder.title
            containerView.order_id.text = "Заказ №" + myOrder.id.toString()
            itemView.setOnClickListener { clickListener?.invoke(myOrder) }
        }
    }
}
