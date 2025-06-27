package com.clarkelamothe.notemark.core.data.di

import com.clarkelamothe.notemark.core.data.auth.EncryptedSessionStorage
import com.clarkelamothe.notemark.core.domain.SessionStorage
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val coreDataModule = module {
    singleOf(::EncryptedSessionStorage).bind<SessionStorage>()
}
