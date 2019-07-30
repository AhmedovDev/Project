package com.example.optovik.presentation.screens.main.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.optovik.R
import com.example.optovik.data.global.models.Category
import com.example.optovik.data.global.models.DataModel
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_category.view.*

private typealias OnCategoryClickListener = ((Category) -> Unit)

class CategoryAdapter(private val categoryes: List<Category>) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private var clickListener: OnCategoryClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) =
        holder.bind(categoryes[position], clickListener)

    override fun getItemCount(): Int = categoryes.size

    fun setOnCategoryClickListener(listener: OnCategoryClickListener?) {
        clickListener = listener
    }

    class CategoryViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(categoryes: Category, clickListener: OnCategoryClickListener?) {
            Picasso.get()
                .load(categoryes.image)
                .placeholder(R.drawable.box)
                .into(containerView.image_category)
            containerView.text_category.text = categoryes.name
            itemView.setOnClickListener { clickListener?.invoke(categoryes)}

        }
    }
}