package com.blackboardtheory.notifications.components

import android.content.SharedPreferences
import com.blackboardtheory.notifications.models.SimpleNotification
import com.google.gson.Gson
import com.google.gson.JsonParseException
import com.google.gson.reflect.TypeToken

class SimpleNotificationRepository(val prefs: SharedPreferences): NotificationRepository {

    companion object {
        const val KEY_NOTIFICATIONS = "simple_notifications"
    }

    override fun addNotification(notification: SimpleNotification) {
        val notificationExists = getNotificationByID(notification.id) != null
        if(!notificationExists) {
            val notifications = getNotifications()
            notifications.add(notification)
            prefs.edit().putString(KEY_NOTIFICATIONS, Gson().toJson(notifications)).apply()
        }
    }

    override fun getNotificationsForGroup(group: String): MutableList<SimpleNotification> {
        val jsonString = prefs.getString(KEY_NOTIFICATIONS, "")
        try {
            val listType = object : TypeToken<ArrayList<SimpleNotification>>() {}.type
            val list: ArrayList<SimpleNotification>? = Gson().fromJson(jsonString, listType)
            list?.let { notifications ->
                return ArrayList(notifications.filter {it.group == group})
            }
            return ArrayList()
        } catch(e: JsonParseException) {
            return ArrayList()
        }
    }

    override fun getNotificationByID(id: Int): SimpleNotification? {
        return getNotifications().filter{ it.id == id }.firstOrNull()
    }

    override fun removeNotificationByID(id: Int) {
        val notifications = getNotifications()
        val candidate = getNotificationByID(id)
        candidate?.let {
            notifications.remove(it)
        }
        prefs.edit().putString(KEY_NOTIFICATIONS, Gson().toJson(notifications)).apply()
    }

    override fun clearNotifications() {
        prefs.edit().clear().apply()
    }

    override fun getNotificationCountForGroup(group: String): Int {
        return getNotifications().filter{it.group == group}.size
    }

    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/

    private fun getNotifications(): MutableList<SimpleNotification> {
        val jsonString = prefs.getString(KEY_NOTIFICATIONS, "")
        try {
            val listType = object : TypeToken<ArrayList<SimpleNotification>>() {}.type
            val list: ArrayList<SimpleNotification>? = Gson().fromJson(jsonString, listType)
            return list ?: ArrayList()
        } catch(e: JsonParseException) {
            return ArrayList()
        }
    }
}