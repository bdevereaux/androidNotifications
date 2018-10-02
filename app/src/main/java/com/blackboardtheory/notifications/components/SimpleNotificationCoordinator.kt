package com.blackboardtheory.notifications.components

import android.content.Context
import android.media.RingtoneManager
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.blackboardtheory.notifications.NotificationsApplication
import com.blackboardtheory.notifications.R
import com.blackboardtheory.notifications.models.SimpleNotification

class SimpleNotificationCoordinator(private val context: Context) : NotificationCoordinator {

    private val mNotificationManager: NotificationManagerCompat

    init {
        mNotificationManager = NotificationManagerCompat.from(context)
    }

    override fun addNotification(notification: SimpleNotification) {
        val builder = NotificationCompat.Builder(context, NotificationsApplication.CHANNEL_ID)
                .setStyle(NotificationCompat.BigTextStyle().bigText(notification.title))
                .setSmallIcon(R.drawable.ic_android)
                .setContentTitle(notification.title)
                .setContentText(notification.subtitle)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setAutoCancel(true)
                .setVibrate(longArrayOf(500, 250, 500, 250, 500, 250))
                .setGroup(notification.group)

        mNotificationManager.notify(notification.id, builder.build())
    }
}