package ru.diitcenter.optovik.presentation.screens.productcard.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_product_card_image.view.*
import ru.example.optovik.R


class ProductCardAdapter(private val productCard: List<String>) :
    RecyclerView.Adapter<ProductCardAdapter.ProductCardViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductCardViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_product_card_image, parent, false)
        return ProductCardViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProductCardViewHolder, position: Int) =
        holder.bind(productCard[position])

    // todo
    override fun getItemCount(): Int = productCard.size


    class ProductCardViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(image: String) {

                Picasso.get()
                    .load(image)
                    .fit()
                    .centerCrop()
                    //  .placeholder(R.drawable.box)
                    .into(containerView.image_product_card)



        }
    }
}


