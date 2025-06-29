package com.clarkelamothe.notemark.feature_auth.data

import com.clarkelamothe.notemark.core.domain.AuthInfo
import com.clarkelamothe.notemark.core.domain.SessionStorage
import com.clarkelamothe.notemark.core.domain.util.DataError
import com.clarkelamothe.notemark.core.domain.util.EmptyResult
import com.clarkelamothe.notemark.core.domain.util.Result
import com.clarkelamothe.notemark.core.domain.util.asEmptyDataResult
import com.clarkelamothe.notemark.core.networking.post
import com.clarkelamothe.notemark.feature_auth.data.model.LoginRequestBody
import com.clarkelamothe.notemark.feature_auth.data.model.LoginResponse
import com.clarkelamothe.notemark.feature_auth.data.model.RegisterRequestBody
import com.clarkelamothe.notemark.feature_auth.domain.AuthRepository
import io.ktor.client.HttpClient

class AuthRepositoryImpl(
    private val client: HttpClient,
    private val sessionStorage: SessionStorage
) : AuthRepository {
    override suspend fun login(
        email: String,
        password: String
    ): EmptyResult<DataError.Network> {
        val result = client.post<LoginRequestBody, LoginResponse>(
            route = "/api/auth/login",
            body = LoginRequestBody(email, password)
        )
        if (result is Result.Success) {
            sessionStorage.set(
                AuthInfo(
                    accessToken = result.data.accessToken,
                    refreshToken = result.data.refreshToken
                )
            )
        }
        return result.asEmptyDataResult()
    }

    override suspend fun register(
        username: String,
        email: String,
        password: String
    ): EmptyResult<DataError.Network> = client.post<RegisterRequestBody, Unit>(
        route = "/api/auth/register",
        body = RegisterRequestBody(username, email, password)
    )
}
