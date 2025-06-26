package com.clarkelamothe.notemark.feature_auth.di

import com.clarkelamothe.notemark.feature_auth.data.AuthRepositoryImpl
import com.clarkelamothe.notemark.feature_auth.data.EmailPatternValidator
import com.clarkelamothe.notemark.feature_auth.domain.AuthRepository
import com.clarkelamothe.notemark.feature_auth.domain.PatternValidator
import com.clarkelamothe.notemark.feature_auth.domain.UserDataValidator
import com.clarkelamothe.notemark.feature_auth.presentation.AuthViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val authModule = module {
    single<PatternValidator> {
        EmailPatternValidator
    }
    singleOf(::UserDataValidator)

    viewModelOf(::AuthViewModel)
    singleOf(::AuthRepositoryImpl).bind<AuthRepository>()
}
