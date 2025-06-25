package com.clarkelamothe.notemark.app

import android.app.Application
import com.clarkelamothe.notemark.feature_auth.di.authModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)

            modules(
                authModule
            )
        }
    }
}