package com.blackboardtheory.notifications.di

import com.blackboardtheory.notifications.notificationList.di.NotificationsComponent
import com.blackboardtheory.notifications.notificationList.di.NotificationsModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules=[AppModule::class])
interface AppComponent {
    fun plus(notificationsModule: NotificationsModule): NotificationsComponent
}