package com.clarkelamothe.notemark.app.navigation

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object Auth : Route

    @Serializable
    data object Home : Route

    @Serializable
    data object Onboarding : Route

    @Serializable
    data object Login : Route

    @Serializable
    data object Register : Route

    @Serializable
    data object Notes : Route

    @Serializable
    data class Note(val id: String?) : Route
}
