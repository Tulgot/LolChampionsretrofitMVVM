package com.tulgot.lol.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.tulgot.lol.domain.SETTINGS_NAME
import com.tulgot.lol.domain.datastore.DataStoreManager
import kotlinx.coroutines.flow.first
import javax.inject.Inject

private val Context.dataStore by preferencesDataStore(name = SETTINGS_NAME)

class DefaultDataStoreManager @Inject constructor(
    private val context: Context
) : DataStoreManager {

    override suspend fun putSettings(key: String, value: Boolean) {
        val preferencesKey = booleanPreferencesKey(key)
        context.dataStore.edit {
            it[preferencesKey] = value
        }
    }

    override suspend fun getSettings(key: String): Boolean? {
        return try {
            val preferenceKey = booleanPreferencesKey(key)
            val preferences = context.dataStore.data.first()
            preferences[preferenceKey]
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override suspend fun putDeviceCheckBox(key: String, value: Boolean) {
        val preferenceskey = booleanPreferencesKey(key)
        context.dataStore.edit {
            it[preferenceskey] = value
        }
    }

    override suspend fun getDeviceCheckBox(key: String): Boolean? {
        return try {
            val preferenceKey = booleanPreferencesKey(key)
            val preferences = context.dataStore.data.first()
            preferences[preferenceKey]
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}