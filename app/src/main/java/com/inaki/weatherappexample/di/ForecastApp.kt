package com.inaki.weatherappexample.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ForecastApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@ForecastApp)
            modules(listOf(networkModule, viewModelModule))
        }
    }
}