package com.example.optovik.presentation.screens.notofication.ui

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.optovik.R
import com.example.optovik.data.global.models.Notification
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_notification.*
import kotlinx.android.synthetic.main.item_notification.view.*
import kotlinx.android.synthetic.main.item_notification.view.border
import java.text.SimpleDateFormat
import java.util.*

var lastDate = ""

class NotificationAdapter(private val notification: List<Notification>) :
    RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_notification, parent, false)
        return NotificationViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) =
        holder.bind(notification[position])

    override fun getItemCount(): Int = notification.size

    class NotificationViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun yesterday(): Date {
            var cal = Calendar.getInstance()
            cal.add(Calendar.DATE, -1)
            return cal.getTime()
        }


        @SuppressLint("SimpleDateFormat")
        fun bind(notification: Notification) {
            var date = SimpleDateFormat("dd.MM.yyyy")
            var currentDate = date.format(Date())
            var yesterday = date.format(yesterday())
            if (notification.date == lastDate) containerView.date_notification.visibility = View.GONE
            if (notification.date == currentDate) {
                containerView.date_notification.text = "Сегодня"
                lastDate = notification.date
            }
            if (notification.date == yesterday) {
                containerView.date_notification.text = "Вчера"
                lastDate = notification.date
            }
            if(notification.date!=yesterday && notification.date != currentDate) {
                containerView.date_notification.text = notification.date
                lastDate = notification.date
            }
            containerView.time_notification.text = notification.time
            containerView.information_notification.text = notification.information


        }
    }
}