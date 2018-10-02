package com.blackboardtheory.notifications.notificationList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.blackboardtheory.notifications.NotificationsApplication
import com.blackboardtheory.notifications.components.NotificationCoordinator
import com.blackboardtheory.notifications.models.SimpleNotification
import com.blackboardtheory.notifications.notificationList.di.NotificationsModule
import javax.inject.Inject

class NotificationsViewModel : ViewModel(), SimpleNotificationItemListener {

    var notifications: MutableLiveData<MutableList<SimpleNotification>> = MutableLiveData()
        private set

    @Inject
    lateinit var mNotificationCoordinator: NotificationCoordinator

    init {
        notifications.value = ArrayList()
        NotificationsApplication.appComponent?.plus(NotificationsModule())?.inject(this)

    }

    fun addNotification(notification: SimpleNotification) {
        notifications.value?.add(notification)
        notifications.postValue(notifications.value)
    }

    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/

    override fun onClick(notification: SimpleNotification) {
        mNotificationCoordinator.addNotification(notification)
    }

    override fun onLongClick(notification: SimpleNotification) {
        // todo show the channel id on long click
    }
}