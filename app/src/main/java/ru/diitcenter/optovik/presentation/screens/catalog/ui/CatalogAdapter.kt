package ru.diitcenter.optovik.presentation.screens.catalog.ui

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import com.squareup.picasso.Picasso
import com.squareup.picasso.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_catalog.*
import kotlinx.android.synthetic.main.item_catalog.view.*
import ru.diitcenter.optovik.presentation.global.utils.hideKeyboard

private  var sizeList = 0
private typealias OnCategoryClickListener = ((ru.diitcenter.optovik.data.global.models.Product) -> Unit)

class CatalogAdapter(
    private val products: List<ru.diitcenter.optovik.data.global.models.Product>,
    private val clickListenerPlus: (ru.diitcenter.optovik.data.global.models.Product) -> Unit,
    private val clickListenerMinus: (ru.diitcenter.optovik.data.global.models.Product) -> Unit,
    private val basket: ru.diitcenter.optovik.data.basketholder.BasketHolder
) :
    RecyclerView.Adapter<CatalogAdapter.CatalogViewHolder>() {

    private var clickListener: OnCategoryClickListener? = null

    private var isFirstOpen = true

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(ru.example.optovik.R.layout.item_catalog, parent, false)

        sizeList = products.size

        return CatalogViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CatalogViewHolder, position: Int) {
        // todo сделать нормально
        holder.plusClick(products[position])
        holder.minusClick(products[position])
        holder.bind(products[position], clickListener!!)

//        holder.keyboardHide()


    }

    override fun getItemCount(): Int = products.size

    fun setOnCatalogClickListener(listener: OnCategoryClickListener?) {
        clickListener = listener
    }


    inner class CatalogViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        // private var loaderCounter: Int = 0

        var sum = 0

        lateinit var product: ru.diitcenter.optovik.data.global.models.Product

        fun plusClick(product: ru.diitcenter.optovik.data.global.models.Product) {
            with(containerView) {


                val item = basket.items.filter { it.product.id == product.id }.firstOrNull()



                plus.setOnClickListener {
                    if (input_product.text.toString() == "")
                        input_product.setText("0")
                    else {
                        if (item != null) {
                            sum = item.quantity
                        } else
                            sum = 0
                        //  sum = input_product.text.toString().toInt()
                        minus.visibility = View.VISIBLE
                        input_product.visibility = View.VISIBLE
                        sum += 1
                        plus.isEnabled = false
                        basket.addProduct(product) {
                            if (!it)
                                sum -= 1
                            plus.isEnabled = true
                        }
                        input_product.setText("$sum")
                        clickListenerPlus(product)
                        notifyDataSetChanged()
                    }
                }
            }

        }

        fun minusClick(product: ru.diitcenter.optovik.data.global.models.Product) {
            minus.setOnClickListener {
                val item = basket.items.filter { it.product.id == product.id }.firstOrNull()

                if (input_product.text.toString() == "" || input_product.text.toString() == "0") {
                    minus.visibility = View.GONE
                } else {
                    minus.isEnabled = true
                    if (item != null) {
                        sum = item.quantity
                    }
                    else
                        sum = 0
                   // sum = input_product.text.toString().toInt()
                    sum -= 1
                    basket.deleteProduct(product) {
                        if (!it)
                            sum += 1
                    }
                    if (sum == 0) {
                        minus.visibility = View.GONE
                        input_product.visibility = View.GONE
                        input_product.setText("$sum")
                    }
                    input_product.setText("$sum")
                    clickListenerMinus(product)
                    notifyDataSetChanged()
                }
            }
        }


//        fun keyboardHide() {
//            containerView.input_product.setOnEditorActionListener { _, actionId, _ ->
//                if (actionId == EditorInfo.IME_ACTION_DONE) {
//                    hideKeyboard(containerView.context!!, containerView)
//                }
//                false
//            }
//        }


        @SuppressLint("ResourceAsColor")
        fun bind(
            product: ru.diitcenter.optovik.data.global.models.Product,
            clickListener: OnCategoryClickListener
        ) {

            this.product = product

            val item = basket.items.filter { it.product.id == product.id }.firstOrNull()

            if (item != null) {
                if (isFirstOpen) {
                    sum = item.quantity
                }
                isFirstOpen = false
                containerView.input_product.setText("${item.quantity}")
                containerView.input_product.visibility = View.VISIBLE
                containerView.minus.visibility = View.VISIBLE

            }
            else{
                containerView.input_product.setText("${0}")
                containerView.input_product.visibility = View.GONE
                containerView.minus.visibility = View.GONE

            }

            Picasso.get()
                .load(product.image)
                .into(containerView.image_product)
            containerView.product_name.text = product.name
            containerView.price.text = "%,d".format(product.price)
            containerView.count_product.text = product.count
            if (product.quantity != null) {
                containerView.input_product.visibility = View.VISIBLE
                containerView.minus.visibility = View.VISIBLE
                containerView.input_product.setText("${product.quantity}")
            }
            if (product.presence == false) {
                containerView.plus.visibility = View.GONE
                containerView.minus.visibility = View.GONE
                containerView.input_product.visibility = View.VISIBLE
                containerView.input_product.maxEms = 6
                containerView.input_product.mask = "#############"
                containerView.input_product.setTextColor(ru.example.optovik.R.color.colorTextHint)
                containerView.input_product.setText("Нет в наличии")
                containerView.input_product.isEnabled = false
            }
            val isEstimatedPrice = product.isEstimatedPrice
            if (isEstimatedPrice) containerView.isEstimatedPrise.visibility = View.VISIBLE

            if (input_product.text.toString() == "") minus.visibility = View.GONE

            itemView.setOnClickListener {
                clickListener.invoke(product)
            }

            if(adapterPosition == sizeList-1)
                bottom_border_catalog.visibility = View.GONE
        }
    }
}