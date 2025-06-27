package com.clarkelamothe.notemark.core.data.auth

import android.content.SharedPreferences
import androidx.core.content.edit
import com.clarkelamothe.notemark.app.dispatcher.DispatcherProvider
import com.clarkelamothe.notemark.core.domain.AuthInfo
import com.clarkelamothe.notemark.core.domain.SessionStorage
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

class EncryptedSessionStorage(
    private val sharedPreferences: SharedPreferences,
    private val dispatcher: DispatcherProvider
) : SessionStorage {
    override suspend fun get() = withContext(dispatcher.io) {
        val json = sharedPreferences.getString(KEY_AUTH_INFO, null)
        json?.let {
            Json.decodeFromString<AuthInfoSerializable>(it).toAuthInfo()
        }
    }

    override suspend fun set(info: AuthInfo?) = withContext(dispatcher.io) {
        if (info == null) {
            sharedPreferences.edit(commit = true) { remove(KEY_AUTH_INFO) }
            return@withContext
        }

        val json = Json.encodeToString(info.toAuthInfoSerializable())

        sharedPreferences
            .edit(commit = true) {
                putString(KEY_AUTH_INFO, json)
            }
    }

    companion object {
        private const val KEY_AUTH_INFO = "KEY_AUTH_INFO"
    }
}
