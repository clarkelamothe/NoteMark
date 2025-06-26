package com.clarkelamothe.notemark.feature_auth.data.model

import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequestBody(
    val username: String,
    val email: String,
    val password: String
)
