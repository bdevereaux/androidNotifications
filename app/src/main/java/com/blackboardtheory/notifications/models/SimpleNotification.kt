package com.blackboardtheory.notifications.models

data class SimpleNotification(
        val title: String,
        val subtitle: String,
        val id: Int,
        val channel: String,
        val group: String
)