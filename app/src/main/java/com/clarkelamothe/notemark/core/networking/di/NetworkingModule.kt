package com.clarkelamothe.notemark.core.networking.di

import com.clarkelamothe.notemark.core.networking.HttpClientFactory
import org.koin.dsl.module

val networkingModule = module {
    single { HttpClientFactory(get()).build() }
}
