package com.clarkelamothe.notemark.feature_auth.data.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequestBody(
    val email: String,
    val password: String
)
