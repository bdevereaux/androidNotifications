package com.blackboardtheory.notifications.notificationList.di

import android.content.Context
import com.blackboardtheory.notifications.components.NotificationCoordinator
import com.blackboardtheory.notifications.components.SimpleNotificationCoordinator
import dagger.Module
import dagger.Provides

@Module
class NotificationsModule {

    @Provides
    fun provideNotificationCoordinator(context: Context) : NotificationCoordinator {
        return SimpleNotificationCoordinator(context)
    }
}