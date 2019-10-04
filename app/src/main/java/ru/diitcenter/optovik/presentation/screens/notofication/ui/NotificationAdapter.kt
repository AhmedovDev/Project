package ru.diitcenter.optovik.presentation.screens.notofication.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_notification.*
import kotlinx.android.synthetic.main.item_notification.view.*
import ru.diitcenter.optovik.data.global.models.Notification
import ru.example.optovik.R
import ru.example.optovik.R.drawable.*
import java.util.*


private typealias OnNotificationClickListener = ((ru.diitcenter.optovik.data.global.models.Notification) -> Unit)


class NotificationAdapter(private val notification: List<ru.diitcenter.optovik.data.global.models.Notification>) :
    RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {

    private var clickListenerNotifi: OnNotificationClickListener? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_notification, parent, false)
        return NotificationViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        if (position == 0) {
            holder.bind(null, notification[position], clickListenerNotifi)
        } else {
            holder.bind(notification[position - 1], notification[position], clickListenerNotifi)
        }

    }


    override fun getItemCount(): Int = notification.size

    fun setOnAdresClickListener(listener: OnNotificationClickListener?) {
        clickListenerNotifi = listener

    }

    class NotificationViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun yesterday(): Date {
            var cal = Calendar.getInstance()
            cal.add(Calendar.DATE, -1)
            return cal.getTime()
        }


        fun bind(
            lastItem: Notification? = null,
            notification: Notification,
            clickListener: OnNotificationClickListener?
        ) {
            if (notification.type == "stock" || notification.type == "mailing")
                image_notification.setImageDrawable(containerView.resources.getDrawable(ic_stock))
            if (notification.type == "status") {
                if (notification.order_status == "accepted")
                    image_notification.setImageDrawable(containerView.resources.getDrawable(ic_phone))
                if (notification.order_status == "onroad")
                    image_notification.setImageDrawable(containerView.resources.getDrawable(ic_car))
                if (notification.order_status == "canceled")
                    image_notification.setImageDrawable(containerView.resources.getDrawable(ic_exit))
            }

            if ((lastItem != null && notification.date != lastItem.date) || lastItem == null) {
                date_notification.text = notification.date
                date_notification_in.visibility = View.VISIBLE
//                borderTop.visibility = View.VISIBLE
            } else {
                date_notification_in.visibility = View.GONE
//                      borderTop.visibility = View.GONE
            }

//            var date = SimpleDateFormat("dd.MM.yyyy")
//            var currentDate = date.format(Date())
//            var yesterday = date.format(yesterday())
//            if (notification.date == lastDate)
//                containerView.date_notification_in.visibility = View.GONE
//            if (notification.date == currentDate) {
//                containerView.date_notification.text = "Сегодня"
//                lastDate = notification.date
//            }
//            if (notification.date == yesterday) {
//                containerView.date_notification.text = "Вчера"
//                lastDate = notification.date
//            }
//            if (notification.date != yesterday && notification.date != currentDate) {
//                containerView.date_notification.text = notification.date
//                lastDate = notification.date
//            }
            containerView.time_notification.text = notification.time
            containerView.information_notification.text = notification.information

            itemView.setOnClickListener { clickListener?.invoke(notification) }


        }
    }
}