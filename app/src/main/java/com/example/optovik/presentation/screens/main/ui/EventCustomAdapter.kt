package com.example.optovik.presentation.screens.main.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.optovik.R
import com.example.optovik.data.global.EventData
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_event.view.*


class EventCustomAdapter(val userList: ArrayList<EventData>) :
    RecyclerView.Adapter<EventCustomAdapter.UserViewHolder>() {

    override fun onBindViewHolder(p0: UserViewHolder, p1: Int) {

        p0.bind(userList[p1])

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_event, parent, false)
        return UserViewHolder(itemView)
    }



    override fun getItemCount(): Int = userList.size



    class UserViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(user: EventData) {
            Picasso.get()
                .load(user.image)
                .fit()
                .centerCrop()
                .placeholder(R.drawable.box)
                .into(containerView.image_event)

        }
    }
}