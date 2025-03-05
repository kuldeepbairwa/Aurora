package com.kuldeep.aurora.core.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.kuldeep.aurora.core.domain.repository.DataStoreRepository
import com.kuldeep.aurora.core.utils.DataStoreKeys
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreRepoImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : DataStoreRepository {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATASTORE_NAME)

    override suspend fun setUserPhone(phone: String) {
        context.dataStore.edit { prefs ->
            prefs[DataStoreKeys.USER_PHONE] = phone
        }
    }

    override suspend fun getUserPhoneFlow(): Flow<String> {
        return context.dataStore.data.map { prefs ->
            prefs[DataStoreKeys.USER_PHONE] ?: ""
        }
    }

    override suspend fun getUserPhone(): String {
        return context.dataStore.data.map { prefs ->
            prefs[DataStoreKeys.USER_PHONE] ?: ""
        }.firstOrNull() ?: ""
    }

    companion object {
        const val DATASTORE_NAME = "auroraPrefs"
    }
}