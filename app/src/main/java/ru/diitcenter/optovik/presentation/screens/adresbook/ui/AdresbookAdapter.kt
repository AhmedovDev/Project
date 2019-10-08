package ru.diitcenter.optovik.presentation.screens.adresbook.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_adresbook.*
import kotlinx.android.synthetic.main.item_adresbook.view.*
import kotlinx.android.synthetic.main.item_adresbook.view.item_addressbook
import ru.example.optovik.R


private typealias OnAdresClickListener = ((ru.diitcenter.optovik.data.global.models.Location) -> Unit)

private var mSelectedItem = -1

class AdresbookAdapter(private val location: List<ru.diitcenter.optovik.data.global.models.Location>, private var prefsHelper: ru.diitcenter.optovik.data.prefs.PrefsHelper) :
    RecyclerView.Adapter<AdresbookAdapter.AdresbookViewHolder>() {

    private var clickListener: OnAdresClickListener? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdresbookViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_adresbook, parent, false)

        return AdresbookViewHolder(itemView)
    }

    fun getSelectedItem(): Int {
        return mSelectedItem
    }

    override fun onBindViewHolder(holder: AdresbookViewHolder, position: Int) {
        holder.bind(location[position], clickListener, mSelectedItem)
    }


    override fun getItemCount(): Int = location.size

    fun setOnAdresClickListener(listener: OnAdresClickListener?) {
        clickListener = listener
    }


    inner class AdresbookViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(
            location: ru.diitcenter.optovik.data.global.models.Location,
            clickListener: OnAdresClickListener?,
            selectedPosition: Int
        ) {
            containerView.adres.text = location.address
            containerView.phone_in_adresbook.text = location.phone

            if ((selectedPosition == -1 && adapterPosition == 0))
                itemView.radioButton_adresbook.setChecked(false)
            else
                if (selectedPosition == adapterPosition)
                    itemView.radioButton_adresbook.setChecked(true)
                else
                    itemView.radioButton_adresbook.setChecked(false)


            itemView.setOnClickListener {

                mSelectedItem = getAdapterPosition()
                // todo доработать
                prefsHelper.saveAddress(location.address)
              //  prefsHelper.savePhone(location.phone)
                notifyDataSetChanged()
                clickListener?.invoke(location)
            }
            if(location.address == prefsHelper.getAddress() && location.phone == prefsHelper.getPhone())
                containerView.radioButton_adresbook.isChecked = true



        }
    }
}
