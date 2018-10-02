package com.blackboardtheory.notifications.notificationList.di

import com.blackboardtheory.notifications.notificationList.NotificationsViewModel
import dagger.Subcomponent

@Subcomponent(modules=[NotificationsModule::class])
interface NotificationsComponent {
    fun inject(viewModel: NotificationsViewModel)
}