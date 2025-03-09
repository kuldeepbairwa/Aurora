package com.kuldeep.aurora.features.chatList.domain

import com.kuldeep.aurora.core.domain.repository.DataStoreRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val dateStoreRepository: DataStoreRepository
) {
    suspend operator fun invoke() {
        dateStoreRepository.clearDataStorePrefs()
    }
}