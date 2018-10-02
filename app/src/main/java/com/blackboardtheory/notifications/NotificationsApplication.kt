package com.blackboardtheory.notifications

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import androidx.core.app.NotificationManagerCompat
import com.blackboardtheory.notifications.di.AppComponent
import com.blackboardtheory.notifications.di.AppModule
import com.blackboardtheory.notifications.di.DaggerAppComponent

class NotificationsApplication : Application() {


    companion object {
        var appComponent: AppComponent? = null
            private set
        const val CHANNEL_ID: String = "sample_notifications_channel"
        const val CHANNEL_NAME: String = "BlackboardTheory Notifications"
        const val CHANNEL_DESC: String = "Responsible for displaying sample notifications for the Notifications app"
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
        registerNotificationChannel()
    }

    private fun registerNotificationChannel() {
        val mChannel: NotificationChannel
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {// all notifications from Android O onward have to have a channel
            val importance = NotificationManager.IMPORTANCE_HIGH
            mChannel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance)
            mChannel.description = CHANNEL_DESC
            mChannel.enableLights(true)
            mChannel.lightColor = Color.GREEN
            mChannel.enableVibration(true)
            mChannel.vibrationPattern = longArrayOf(500, 250, 500, 250, 500, 250)
            val mNotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            mNotificationManager.createNotificationChannel(mChannel)
        }
    }
}