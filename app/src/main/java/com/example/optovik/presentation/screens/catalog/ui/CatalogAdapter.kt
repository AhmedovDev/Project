package com.example.optovik.presentation.screens.catalog.ui

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
import kotlinx.android.synthetic.main.activity_catalog.view.*
import kotlinx.android.synthetic.main.item_catalog.*
import kotlinx.android.synthetic.main.item_catalog.view.*

private typealias OnCategoryClickListener = ((Products) -> Unit)
lateinit var basket: Basket

class CatalogAdapter(private val products: List<Products>) :
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
        holder.PlusClick()
        holder.MinusClick()
        holder.keyboardHide()
    }

    override fun getItemCount(): Int = products.size

    fun setOnCatalogClickListener(listener: OnCategoryClickListener?) {
        clickListener = listener
    }


    class CatalogViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun PlusClick() {
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
                }
            }
        }

        fun MinusClick() {
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


        fun keyboardHide() {
            containerView.input_product.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    hideKeyboard(containerView.context!!, containerView)
                }
                false
            }
        }


        @SuppressLint("ResourceAsColor")
        fun bind(products: Products, clickListener: OnCategoryClickListener) {
            Picasso.get()
                .load(products.image)
                .placeholder(R.drawable.box)
                .into(containerView.image_product)
            containerView.product_name.text = products.name
            containerView.price.text = products.price.toString()
            containerView.count_product.text = products.count
            var presence = products.presence
            if (presence == false) {
                containerView.plus.visibility = View.GONE
                containerView.input_product.visibility = View.VISIBLE
                containerView.input_product.maxEms = 6
                containerView.input_product.setTextColor(R.color.colorTextHint)
                containerView.input_product.setText("Нет в наличии")
                containerView.input_product.isEnabled = false
            }
            var isEstimatedPrice = products.isEstimatedPrice
            if (isEstimatedPrice == true) containerView.isEstimatedPrise.visibility = View.VISIBLE

            if (input_product.text.toString() == "") minus.visibility = View.GONE

            containerView.image_product.setOnClickListener { clickListener.invoke(products) }
        }
    }
}