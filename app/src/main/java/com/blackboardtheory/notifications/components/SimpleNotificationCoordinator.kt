package com.blackboardtheory.notifications.components

import android.content.Context
import android.media.RingtoneManager
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.blackboardtheory.notifications.NotificationsApplication
import com.blackboardtheory.notifications.R
import com.blackboardtheory.notifications.models.SimpleNotification

class SimpleNotificationCoordinator(private val context: Context, private val repo: NotificationRepository) : NotificationCoordinator {

    companion object {
        const val SUMMARY_ID: Int = 0x05CAFACE// use hashcode of the group ID as our summary ID
    }

    private val mNotificationManager: NotificationManagerCompat = NotificationManagerCompat.from(context)

    override fun addNotification(notification: SimpleNotification) {
        repo.addNotification(notification)
        val unreadNotificationCount = repo.getNotificationCountForGroup(notification.group)
        if(unreadNotificationCount > 1) {// We have 2 or more active notifications
            postNotification(notification)
            postSummaryNotification(notification.group)// Post or update the summary notification
            // todo do we also post an individual notification here?
        } else {
            postNotification(notification)
        }
    }

    override fun clearAllNotifications() {
        repo.clearNotifications()
    }

    /**********************************************************************************************/
    /************************************ Private Methods *****************************************/
    /**********************************************************************************************/

    private fun postNotification(notification: SimpleNotification, useSound: Boolean = true) {
        val builder = NotificationCompat.Builder(context, NotificationsApplication.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_android)
                .setContentTitle(notification.title)
                .setContentText(notification.subtitle)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setAutoCancel(true)
                .setVibrate(longArrayOf(500, 250, 500, 250, 500, 250))
                .setStyle(NotificationCompat.BigTextStyle().bigText(notification.title))
                .setGroup(notification.group)

        mNotificationManager.notify(notification.id, builder.build())
    }

    private fun postSummaryNotification(group: String) {
        val builder = NotificationCompat.Builder(context, NotificationsApplication.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_android)
                .setContentTitle("Summary for group: ${group}")
                .setContentText("Summary for group: ${group}")
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setAutoCancel(true)
                .setVibrate(longArrayOf(500, 250, 500, 250, 500, 250))
                .setStyle(NotificationCompat.BigTextStyle().bigText("Summary"))
                .setGroup(group)
                .setGroupSummary(true)
                .setAutoCancel(true)

        mNotificationManager.notify(group.hashCode(), builder.build())// we use the group hashcode as the summary ID
    }
}