package com.example.optovik.presentation.screens.main.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.optovik.R
import com.example.optovik.data.global.User
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_category.view.*

private typealias OnCategoryClickListener = ((User) -> Unit)

class CategoryAdapter(private val users: List<User>) :
    RecyclerView.Adapter<CategoryAdapter.UserViewHolder>() {

    private var clickListener: OnCategoryClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_category, parent, false)
        return UserViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) =
        holder.bind(users[position], clickListener)

    override fun getItemCount(): Int = users.size

    fun setOnUserClickListener(listener: OnCategoryClickListener?) {
        clickListener = listener
    }

    class UserViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(user: User, clickListener: OnCategoryClickListener?) {
            Picasso.get()
                .load(user.image)
                .placeholder(R.drawable.box)
                .into(containerView.image_category)
           containerView.text_category.text = user.login

            itemView.setOnClickListener { clickListener?.invoke(user) }
        }
    }
}