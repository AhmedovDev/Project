package com.example.optovik.presentation.screens.adresbook.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.optovik.R
import com.example.optovik.data.global.models.Event
import com.example.optovik.data.global.models.Location
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_adresbook.view.*

private typealias OnAdresClickListener = ((Location) -> Unit)

class AdresbookAdapter(private val location: List<Location>) :
    RecyclerView.Adapter<AdresbookAdapter.AdresbookViewHolder>() {

    private var clickListener: OnAdresClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdresbookViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_adresbook, parent, false)
        return AdresbookViewHolder(itemView)


    }

    override fun onBindViewHolder(holder: AdresbookViewHolder, position: Int) =
        holder.bind(location[position], clickListener)

    override fun getItemCount(): Int = location.size

    fun setOnAdresClickListener(listener: OnAdresClickListener?) {
        clickListener = listener
    }

    class AdresbookViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(location: Location, clickListener: OnAdresClickListener?) {
            containerView.adres.setText(location.adres)
            containerView.phone_in_adresbook.setText(location.phone)

            itemView.setOnClickListener { clickListener?.invoke(location) }
        }
    }
}
