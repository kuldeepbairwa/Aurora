package com.kuldeep.aurora.features.chatList.domain

import com.kuldeep.aurora.core.domain.repository.DataStoreRepository
import javax.inject.Inject

class GetLoggedInUserUseCase @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
){
    suspend operator fun invoke() = dataStoreRepository.getUserPhoneFlow()
}