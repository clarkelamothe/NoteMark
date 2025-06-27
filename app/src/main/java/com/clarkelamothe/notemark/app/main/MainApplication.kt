package com.clarkelamothe.notemark.app.main

import android.app.Application
import com.clarkelamothe.notemark.app.di.appModule
import com.clarkelamothe.notemark.core.data.di.coreDataModule
import com.clarkelamothe.notemark.core.database.di.databaseModule
import com.clarkelamothe.notemark.core.networking.di.networkingModule
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
                appModule,
                networkingModule,
                databaseModule,
                coreDataModule,
                authModule
            )
        }
    }
}
