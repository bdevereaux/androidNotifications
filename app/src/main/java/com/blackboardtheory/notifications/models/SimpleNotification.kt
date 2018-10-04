package com.blackboardtheory.notifications.models

data class SimpleNotification(
        val title: String,
        val subtitle: String,
        val channel: String,
        val group: String,
        val id: Int
)