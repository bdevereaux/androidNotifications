package com.blackboardtheory.notifications.components

import com.blackboardtheory.notifications.models.SimpleNotification

interface NotificationCoordinator {
    fun addNotification(notification: SimpleNotification)
}