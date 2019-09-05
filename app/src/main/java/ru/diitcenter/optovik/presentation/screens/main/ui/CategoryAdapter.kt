package ru.diitcenter.optovik.presentation.screens.main.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_category.view.*
import ru.example.optovik.R

private typealias OnCategoryClickListener = ((ru.diitcenter.optovik.data.global.models.Category) -> Unit)

class CategoryAdapter(private val categoryes: List<ru.diitcenter.optovik.data.global.models.Category>) :
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

        fun bind(categoryes: ru.diitcenter.optovik.data.global.models.Category, clickListener: OnCategoryClickListener?) {
            Picasso.get()
                .load(categoryes.image)
                .into(containerView.image_category)
            containerView.text_category.text = categoryes.name
            itemView.setOnClickListener { clickListener?.invoke(categoryes) }

        }
    }
}