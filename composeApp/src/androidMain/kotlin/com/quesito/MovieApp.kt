package com.quesito

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class MovieApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin{
            androidLogger(Level.DEBUG)
            androidContext(this@MovieApp)
        }
    }
}