package com.blackboardtheory.notifications.di

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val appContext: Context) {

    @Provides
    fun provideAppContext() : Context {
        return appContext
    }
}