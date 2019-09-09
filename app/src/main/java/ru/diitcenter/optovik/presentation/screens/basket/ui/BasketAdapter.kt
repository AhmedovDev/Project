package ru.diitcenter.optovik.presentation.screens.basket.ui

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_basket.*
import kotlinx.android.synthetic.main.item_basket.view.*
import kotlinx.android.synthetic.main.item_catalog.*
import ru.diitcenter.optovik.presentation.global.utils.hideKeyboard
import ru.example.optovik.R

private typealias OnCategoryClickListener = ((ru.diitcenter.optovik.data.global.models.Product) -> Unit)

class BasketAdapter(
    private val clickListenerPlus: (ru.diitcenter.optovik.data.global.models.Product) -> Unit,
    private val clickListenerMinus: (ru.diitcenter.optovik.data.global.models.Product) -> Unit,
    val basketholder: ru.diitcenter.optovik.data.basketholder.BasketHolder,
    private val clickListenerdrop: (ru.diitcenter.optovik.data.global.models.Product) -> Unit
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
        //   holder.keyboardHide()

    }

    override fun getItemCount(): Int = basketholder.items.size

    fun setOnBasketClickListener(listener: OnCategoryClickListener?) {
        clickListener = listener
    }

    inner class BasketViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun plusClickBasket(product: ru.diitcenter.optovik.data.global.models.Product) {
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

        fun minusClickBasket(
            product: ru.diitcenter.optovik.data.global.models.Product,
            position: Int
        ) {
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


        fun dropClick(product: ru.diitcenter.optovik.data.global.models.Product) {
            drop.setOnClickListener {
                clickListenerdrop(product)
                notifyItemRemoved(adapterPosition)
                notifyDataSetChanged()
            }
        }

//        fun keyboardHide() {
//            containerView.input_product_basket.setOnEditorActionListener { _, actionId, _ ->
//                if (actionId == EditorInfo.IME_ACTION_DONE) {
//                    hideKeyboard(containerView.context!!, containerView)
//                }
//                false
//
//            }
//        }


        @SuppressLint("ResourceAsColor")
        fun bind(
            product: ru.diitcenter.optovik.data.global.models.Product,
            quantity: Int,
            clickListener: OnCategoryClickListener
        ) {
            basketholder.items.forEach { item ->
                Picasso.get()
                    .load(item.product.image)
                    .into(containerView.image_product_basket)
                containerView.product_name_basket.text = item.product.name
                containerView.price_basket.text = "%,d".format(item.product.price)
                containerView.count_product_basket.text = item.product.count
                containerView.input_product_basket.setText("$quantity")
                if (input_product_basket.text.toString() != "") {
                    input_product_basket.visibility = View.VISIBLE
                    minus_basket.visibility = View.VISIBLE
                }


                if (input_product_basket.text.toString() == "") minus.visibility = View.GONE
            }
            val isEstimatedPrice = product.isEstimatedPrice
            if (isEstimatedPrice) containerView.isEstimatedPrise_basket.visibility = View.VISIBLE
            else containerView.isEstimatedPrise_basket.visibility = View.GONE
            itemView.setOnClickListener { clickListener.invoke(product) }
        }
    }
}
