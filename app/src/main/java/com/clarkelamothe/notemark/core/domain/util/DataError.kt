package com.clarkelamothe.notemark.core.domain.util

sealed interface DataError : Error {
    enum class Network : DataError {
        SERIALIZATION,
        NO_INTERNET,
        BAD_REQUEST,
        UNAUTHORIZED,
        METHOD_NOT_ALLOWED,
        CONFLICT,
        TOO_MANY_REQUESTS,
        SERVER_ERROR,
        UNKNOWN
    }
}
