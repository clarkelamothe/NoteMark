package com.clarkelamothe.notemark.feature_auth.domain

import com.clarkelamothe.notemark.core.domain.AuthInfo
import com.clarkelamothe.notemark.core.domain.util.DataError
import com.clarkelamothe.notemark.core.domain.util.EmptyResult
import com.clarkelamothe.notemark.core.domain.util.Result

interface AuthRepository {
    suspend fun login(
        email: String,
        password: String
    ): EmptyResult<DataError.Network>

    suspend fun register(
        username: String,
        email: String,
        password: String
    ): EmptyResult<DataError.Network>
}
