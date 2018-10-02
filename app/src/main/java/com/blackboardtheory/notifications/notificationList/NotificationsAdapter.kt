package com.blackboardtheory.notifications.notificationList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.blackboardtheory.notifications.R
import com.blackboardtheory.notifications.models.SimpleNotification

class NotificationsAdapter(val itemListener: SimpleNotificationItemListener? = null) : RecyclerView.Adapter<SimpleNotificationViewHolder>() {

    var items: List<SimpleNotification> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleNotificationViewHolder {
        return SimpleNotificationViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_simple_notification, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: SimpleNotificationViewHolder, position: Int) {
        val notification = items.get(position)
        holder.bind(notification, onClickListener = View.OnClickListener {
            itemListener?.onClick(notification)
        }, onLongClickListener = View.OnLongClickListener {
            itemListener?.onLongClick(notification)
            true
        })
    }


}