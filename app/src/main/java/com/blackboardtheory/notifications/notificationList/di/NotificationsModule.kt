package com.blackboardtheory.notifications.notificationList.di

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.blackboardtheory.notifications.components.NotificationCoordinator
import com.blackboardtheory.notifications.components.NotificationRepository
import com.blackboardtheory.notifications.components.SimpleNotificationCoordinator
import com.blackboardtheory.notifications.components.SimpleNotificationRepository
import dagger.Module
import dagger.Provides

@Module
class NotificationsModule {

    @Provides
    fun providePreferenceClassification() : String {
        return "simple_notification_preferences"
    }

    @Provides
    fun provideNotificationPreferences(context: Context, classification: String) : SharedPreferences {
        return context.getSharedPreferences(classification, MODE_PRIVATE)
    }

    @Provides
    fun provideNotificationRepository(prefs: SharedPreferences) : NotificationRepository {
        return SimpleNotificationRepository(prefs)
    }

    @Provides
    fun provideNotificationCoordinator(context: Context, repository: NotificationRepository) : NotificationCoordinator {
        return SimpleNotificationCoordinator(context, repository)
    }
}