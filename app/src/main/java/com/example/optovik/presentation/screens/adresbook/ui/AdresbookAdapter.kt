package com.example.optovik.presentation.screens.adresbook.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import com.example.optovik.data.global.models.Location
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_adresbook.*
import kotlinx.android.synthetic.main.item_adresbook.view.*


private typealias OnAdresClickListener = ((Location) -> Unit)

private var mSelectedItem = 0

class AdresbookAdapter(private val location: List<Location>) :
    RecyclerView.Adapter<AdresbookAdapter.AdresbookViewHolder>() {

    private var clickListener: OnAdresClickListener? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdresbookViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(com.example.optovik.R.layout.item_adresbook, parent, false)

        return AdresbookViewHolder(itemView)
    }

    fun getSelectedItem():Int{
        return  mSelectedItem
    }

    override fun onBindViewHolder(holder: AdresbookViewHolder, position: Int) {
        holder.bind(location[position], clickListener , mSelectedItem)
    }



    override fun getItemCount(): Int = location.size

    fun setOnAdresClickListener(listener: OnAdresClickListener?) {
        clickListener = listener
    }





    inner class AdresbookViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

      fun radioBattonIsChecked(position: Int) {
          radioButton_adresbook.isChecked = true
      }
        fun radioBattonIsCheckedNot(position: Int) {
            radioButton_adresbook.isChecked = false
        }

        fun bind(
            location: Location,
            clickListener: OnAdresClickListener?,
            selectedPosition: Int
        ) {
            containerView.adres.text = location.address
            containerView.phone_in_adresbook.text = location.phone

            if ((selectedPosition == -1 && adapterPosition == 0))
                itemView.radioButton_adresbook.setChecked(true)
            else
                if (selectedPosition == adapterPosition)
                    itemView.radioButton_adresbook.setChecked(true)
                else
                    itemView.radioButton_adresbook.setChecked(false)


            itemView.setOnClickListener {

                mSelectedItem=getAdapterPosition()
                notifyDataSetChanged()
            }
        }
    }
}
