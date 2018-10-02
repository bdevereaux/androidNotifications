package com.blackboardtheory.notifications.notificationList.notificationCreation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class CreateNotificationViewModel: ViewModel() {

    val title: MutableLiveData<String> = MutableLiveData()
    val subtitle: MutableLiveData<String> = MutableLiveData()
    val group: MutableLiveData<String> = MutableLiveData()

    fun isValid() : Boolean {
        return title.value != null && subtitle.value != null && group.value != null
            && title.value!!.isNotEmpty() && subtitle.value!!.isNotEmpty() && group.value!!.isNotEmpty()
    }
}