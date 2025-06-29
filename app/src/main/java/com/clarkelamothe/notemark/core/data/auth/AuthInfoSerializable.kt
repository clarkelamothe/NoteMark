package com.clarkelamothe.notemark.core.data.auth

import kotlinx.serialization.Serializable

@Serializable
data class AuthInfoSerializable(
    val accessToken: String,
    val refreshToken: String,
    val username: String
)
