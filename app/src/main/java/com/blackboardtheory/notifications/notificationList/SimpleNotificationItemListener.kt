package com.blackboardtheory.notifications.notificationList

import com.blackboardtheory.notifications.models.SimpleNotification

interface SimpleNotificationItemListener {
    fun onClick(notification: SimpleNotification)
    fun onLongClick(notification: SimpleNotification)
}