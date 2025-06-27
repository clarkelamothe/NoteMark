package com.clarkelamothe.notemark.app.di

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.clarkelamothe.notemark.app.dispatcher.DispatcherProvider
import com.clarkelamothe.notemark.app.dispatcher.StandardDispatchers
import com.clarkelamothe.notemark.app.main.MainViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    single<SharedPreferences> {
        androidApplication().getSharedPreferences("auth-info", MODE_PRIVATE)
    }

    singleOf(::StandardDispatchers).bind<DispatcherProvider>()

    viewModelOf(::MainViewModel)
}
