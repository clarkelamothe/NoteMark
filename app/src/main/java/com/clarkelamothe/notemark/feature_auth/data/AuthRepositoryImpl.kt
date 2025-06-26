package com.clarkelamothe.notemark.feature_auth.data

import com.clarkelamothe.notemark.core.domain.util.DataError
import com.clarkelamothe.notemark.core.domain.util.EmptyResult
import com.clarkelamothe.notemark.core.networking.post
import com.clarkelamothe.notemark.feature_auth.data.model.LoginRequestBody
import com.clarkelamothe.notemark.feature_auth.data.model.RegisterRequestBody
import com.clarkelamothe.notemark.feature_auth.domain.AuthRepository
import io.ktor.client.HttpClient

class AuthRepositoryImpl(
    private val client: HttpClient
) : AuthRepository {
    override suspend fun login(
        username: String,
        password: String
    ) = client.post<LoginRequestBody, Unit>(
        route = "/login",
        body = LoginRequestBody(username, password)
    )

    override suspend fun register(
        username: String,
        email: String,
        password: String
    ): EmptyResult<DataError.Network> = client.post<RegisterRequestBody, Unit>(
        route = "/register",
        body = RegisterRequestBody(username, email, password)
    )
}
