package com.blackboardtheory.notifications.components

import com.blackboardtheory.notifications.models.SimpleNotification

interface NotificationRepository {
    fun addNotification(notification: SimpleNotification)
    fun getNotificationsForGroup(group: String): MutableList<SimpleNotification>
    fun getNotificationByID(id: Int) : SimpleNotification?
    fun removeNotificationByID(id: Int)
    fun clearNotifications()
    fun getNotificationCountForGroup(group: String) : Int
}