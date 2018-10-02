package com.blackboardtheory.notifications.notificationList

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.blackboardtheory.notifications.R
import com.blackboardtheory.notifications.models.SimpleNotification

class SimpleNotificationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val titleTextView: TextView = itemView.findViewById(R.id.title)
    val subtitleTextView: TextView = itemView.findViewById(R.id.subtitle)
    val group: TextView = itemView.findViewById(R.id.group)

    fun bind(notification: SimpleNotification, onClickListener: View.OnClickListener? = null, onLongClickListener: View.OnLongClickListener? = null) {
        titleTextView.text = notification.title
        subtitleTextView.text = notification.subtitle
        group.text = "Group: ${notification.group}"
        itemView.setOnClickListener(onClickListener)
        itemView.setOnLongClickListener(onLongClickListener)
        // todo show Channel ID in a toast on long click
        // todo send notification on click
    }
}