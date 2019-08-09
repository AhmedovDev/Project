package com.example.optovik.presentation.screens.main.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.optovik.R
import com.example.optovik.data.global.models.Event
import com.example.optovik.data.global.models.DataModel
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_event.view.*

private typealias OnEventClickListener = ((Event) -> Unit)

class EventAdapter(private val event: List<Event>) :
    RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    private var clickListener: OnEventClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_event, parent, false)
        return EventViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) =
        holder.bind(event[position], clickListener)

    override fun getItemCount(): Int = event.size

    fun setOnEventClickListener(listener: OnEventClickListener?) {
        clickListener = listener
    }

    class EventViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(event: Event, clickListener: OnEventClickListener?) {
            Picasso.get()
                .load(event.image)
                .into(containerView.image_event)

            itemView.setOnClickListener { clickListener?.invoke(event) }
        }
    }
}