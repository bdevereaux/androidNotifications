package com.blackboardtheory.notifications.notificationList

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.blackboardtheory.notifications.NotificationsApplication
import com.blackboardtheory.notifications.components.NotificationRepository
import com.blackboardtheory.notifications.components.SimpleNotificationCoordinator
import com.blackboardtheory.notifications.notificationList.di.NotificationsModule
import javax.inject.Inject

class NotificationDismissedReceiver : BroadcastReceiver() {

    @Inject
    lateinit var notificationRepository: NotificationRepository

    override fun onReceive(context: Context, intent: Intent) {
        NotificationsApplication.appComponent?.plus(NotificationsModule())?.inject(this)
        if(intent.hasExtra(SimpleNotificationCoordinator.KEY_DISMISSED_ID)) {
            notificationRepository.removeNotificationByID(intent.getIntExtra(SimpleNotificationCoordinator.KEY_DISMISSED_ID, 0))
        }
    }
}