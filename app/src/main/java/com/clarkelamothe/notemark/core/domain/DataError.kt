package com.clarkelamothe.notemark.core.domain

sealed interface DataError : Error {
    enum class Network : DataError {
        BAD_REQUEST,
        UNAUTHORIZED,
        METHOD_NOT_ALLOWED,
        CONFLICT,
        TOO_MANY_REQUESTS,
        UNKNOWN
    }
}
