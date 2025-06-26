package com.clarkelamothe.notemark.feature_auth.domain

import com.clarkelamothe.notemark.core.domain.util.DataError
import com.clarkelamothe.notemark.core.domain.util.EmptyResult

interface AuthRepository {
    suspend fun register(
        username: String,
        email: String,
        password: String
    ): EmptyResult<DataError.Network>
}
