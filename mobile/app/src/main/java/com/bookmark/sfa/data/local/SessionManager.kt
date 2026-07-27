package com.bookmark.sfa.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import javax.inject.Singleton

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "bookmark_prefs")

@Singleton
class SessionManager @Inject constructor(@ApplicationContext private val context: Context) {

    private object Keys {
        val TOKEN = stringPreferencesKey("auth_token")
        val USER_ID = longPreferencesKey("user_id")
        val USER_NAME = stringPreferencesKey("user_name")
        val USER_PHONE = stringPreferencesKey("user_phone")
        val USER_ROLE = stringPreferencesKey("user_role")
        val DAY_STARTED = booleanPreferencesKey("day_started")
        val DAY_START_TIME = stringPreferencesKey("day_start_time")
    }

    fun getToken(): String? = runBlocking {
        context.dataStore.data.first()[Keys.TOKEN]
    }

    suspend fun saveToken(token: String) {
        context.dataStore.edit { it[Keys.TOKEN] = token }
    }

    suspend fun saveUser(id: Long, name: String, phone: String, role: String) {
        context.dataStore.edit {
            it[Keys.USER_ID] = id
            it[Keys.USER_NAME] = name
            it[Keys.USER_PHONE] = phone
            it[Keys.USER_ROLE] = role
        }
    }

    fun getUserName(): String? = runBlocking { context.dataStore.data.first()[Keys.USER_NAME] }
    fun getUserRole(): String? = runBlocking { context.dataStore.data.first()[Keys.USER_ROLE] }
    fun getUserId(): Long? = runBlocking { context.dataStore.data.first()[Keys.USER_ID] }

    suspend fun setDayStarted(started: Boolean, startTime: String? = null) {
        context.dataStore.edit {
            it[Keys.DAY_STARTED] = started
            if (startTime != null) it[Keys.DAY_START_TIME] = startTime
        }
    }

    fun isDayStarted(): Boolean = runBlocking { context.dataStore.data.first()[Keys.DAY_STARTED] ?: false }

    suspend fun clearSession() {
        context.dataStore.edit { it.clear() }
    }

    fun isLoggedIn(): Boolean = getToken() != null
}
