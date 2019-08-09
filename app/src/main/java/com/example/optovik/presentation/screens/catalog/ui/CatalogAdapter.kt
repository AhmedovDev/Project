package com.example.optovik.presentation.screens.catalog.ui

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import com.example.optovik.R
import com.example.optovik.data.basketholder.BasketHolder
import com.example.optovik.data.global.models.Basket
import com.example.optovik.data.global.models.Product
import com.example.optovik.presentation.global.utils.hideKeyboard
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_catalog.*
import kotlinx.android.synthetic.main.item_catalog.view.*

private typealias OnCategoryClickListener = ((Product) -> Unit)

class CatalogAdapter(
    private val products: List<Product>,
    private val clickListenerPlus: (Product) -> Unit,
    private val clickListenerMinus: (Product) -> Unit,
    private val basket: BasketHolder
) :
    RecyclerView.Adapter<CatalogAdapter.CatalogViewHolder>() {

    private var clickListener: OnCategoryClickListener? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_catalog, parent, false)
        return CatalogViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CatalogViewHolder, position: Int) {
        holder.bind(products[position], clickListener!!)
        // todo сделать нормально
        holder.PlusClick(products[position])
        holder.MinusClick(products[position])
        holder.keyboardHide()
    }

    override fun getItemCount(): Int = products.size

    fun setOnCatalogClickListener(listener: OnCategoryClickListener?) {
        clickListener = listener
    }


    inner class CatalogViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        lateinit var product: Product

        fun PlusClick(product: Product) {
            var sum = 0
            with(containerView) {
                plus.setOnClickListener {
                    if (input_product.text.toString() == "")
                        input_product.setText("0")
                    sum = input_product.text.toString().toInt()
                    minus.visibility = View.VISIBLE
                    input_product.visibility = View.VISIBLE
                    sum++
                    input_product.setText("$sum")
                    clickListenerPlus(product)
                }
            }

        }

        fun MinusClick(product: Product) {
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
                    clickListenerMinus(product)
                }

            }
        }


        fun keyboardHide() {
            containerView.input_product.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    hideKeyboard(containerView.context!!, containerView)
                }
                false
            }
        }


        @SuppressLint("ResourceAsColor")
        fun bind(product: Product, clickListener: OnCategoryClickListener) {
            this.product = product
            basket.items.forEach {
                if (it.product.id == product.id) {
                    containerView.input_product.setText("${it.quantity}")
                    containerView.input_product.visibility = View.VISIBLE
                    containerView.minus.visibility = View.VISIBLE
                }
            }
            Picasso.get()
                .load(product.image)
                .into(containerView.image_product)
            containerView.product_name.text = product.name
            containerView.price.text = product.price.toString()
            containerView.count_product.text = product.count
            if (product.quantity != null) {
                containerView.input_product.visibility = View.VISIBLE
                containerView.minus.visibility = View.VISIBLE
                containerView.input_product.setText("${product.quantity}")
            }
            product.quantity
            var presence = product.presence
            if (presence == false) {
                containerView.plus.visibility = View.GONE
                containerView.input_product.visibility = View.VISIBLE
                containerView.input_product.maxEms = 6
                containerView.input_product.mask = "#############"
                containerView.input_product.setTextColor(R.color.colorTextHint)
                containerView.input_product.setText("Нет в наличии")
                containerView.input_product.isEnabled = false
            }
            var isEstimatedPrice = product.isEstimatedPrice
            if (isEstimatedPrice == true) containerView.isEstimatedPrise.visibility = View.VISIBLE

            if (input_product.text.toString() == "") minus.visibility = View.GONE

            containerView.image_product.setOnClickListener { clickListener.invoke(product) }
            containerView.product_name.setOnClickListener { clickListener.invoke(product) }
            containerView.price_and_count.setOnClickListener { clickListener.invoke(product) }
        }
    }
}