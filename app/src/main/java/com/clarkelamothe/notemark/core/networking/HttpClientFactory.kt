package com.clarkelamothe.notemark.core.networking

import com.clarkelamothe.notemark.BuildConfig
import com.clarkelamothe.notemark.core.domain.AuthInfo
import com.clarkelamothe.notemark.core.domain.SessionStorage
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class HttpClientFactory(
    private val sessionStorage: SessionStorage
) {
    fun build(): HttpClient {
        return HttpClient(CIO) {
            install(ContentNegotiation) {
                json(
                    json = Json {
                        ignoreUnknownKeys = true
                    }
                )
            }
            defaultRequest {
                contentType(ContentType.Application.Json)
                header("X-User-Email", BuildConfig.USER_EMAIL)
            }
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        println(message)
                    }

                }
                level = LogLevel.ALL
            }
            install(HttpTimeout) {
                requestTimeoutMillis = 3000
                connectTimeoutMillis = 3000
            }
            install(Auth) {
                bearer {
                    loadTokens {
                        val info = sessionStorage.get()
                        BearerTokens(
                            accessToken = info?.accessToken ?: "",
                            refreshToken = info?.refreshToken ?: ""
                        )
                    }
                    refreshTokens {
                        val info = sessionStorage.get()
                        val response = client.post {
                            markAsRefreshTokenRequest()
                            url("/api/auth/refresh")
                            setBody(
                                AccessTokenRequest(refreshToken = info?.refreshToken ?: "")
                            )
                            header("Debug", true)
                        }.body<AccessTokenResponse>()

                        val newAuthInfo = AuthInfo(
                            accessToken = response.accessToken,
                            refreshToken = info?.refreshToken ?: "",
                            username = info?.username ?: ""
                        )
                        sessionStorage.set(newAuthInfo)
                        BearerTokens(
                            accessToken = newAuthInfo.accessToken,
                            refreshToken = newAuthInfo.refreshToken
                        )
                    }
                }
            }
        }
    }
}
