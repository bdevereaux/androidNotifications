package com.blackboardtheory.notifications.notificationList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.blackboardtheory.notifications.NotificationsApplication
import com.blackboardtheory.notifications.components.NotificationCoordinator
import com.blackboardtheory.notifications.models.SimpleNotification
import com.blackboardtheory.notifications.notificationList.di.NotificationsModule
import java.util.*
import javax.inject.Inject

class NotificationsViewModel : ViewModel(), SimpleNotificationItemListener {

    var notifications: MutableLiveData<MutableList<SimpleNotification>> = MutableLiveData()
        private set

    @Inject
    lateinit var mNotificationCoordinator: NotificationCoordinator

    init {
        notifications.value = ArrayList()
        NotificationsApplication.appComponent?.plus(NotificationsModule())?.inject(this)
        mNotificationCoordinator.clearAllNotifications()
    }

    fun addNotification(title: String, subtitle: String, group: String) {
        notifications.value?.add(SimpleNotification(title, subtitle, NotificationsApplication.CHANNEL_ID, group, Date().hashCode()))
        notifications.postValue(notifications.value)
    }

    fun clearNotifications() {
        mNotificationCoordinator.clearAllNotifications()
    }

    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/

    override fun onClick(notification: SimpleNotification) {
        mNotificationCoordinator.addNotification(notification)
        // todo should we update the notification to indicate it has been posted?
    }

    override fun onLongClick(notification: SimpleNotification) {
        // todo show the channel id on long click
    }
}