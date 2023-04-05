package com.example.touchside.di

import android.app.Application
//
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class TouchSideApp : Application()  {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@TouchSideApp)
            modules(listOf(
                viewModelModule, repoModule, apiModule, retrofitModule
            ))
        }
    }
}