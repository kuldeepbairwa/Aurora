package com.kuldeep.aurora.core.domain.repository

import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {

    suspend fun setUserPhone(phone: String)

    suspend fun getUserPhoneFlow(): Flow<String>

    suspend fun getUserPhone(): String

    suspend fun clearDataStorePrefs()
}