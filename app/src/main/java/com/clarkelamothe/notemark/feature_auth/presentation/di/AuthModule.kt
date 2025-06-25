package com.clarkelamothe.notemark.feature_auth.presentation.di

import com.clarkelamothe.notemark.feature_auth.presentation.AuthViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val authModule = module {
    viewModelOf(::AuthViewModel)
}
