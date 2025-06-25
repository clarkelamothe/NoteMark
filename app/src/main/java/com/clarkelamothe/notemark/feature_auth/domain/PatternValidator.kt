package com.clarkelamothe.notemark.feature_auth.domain

interface PatternValidator {
    fun matches(value: String): Boolean
}
