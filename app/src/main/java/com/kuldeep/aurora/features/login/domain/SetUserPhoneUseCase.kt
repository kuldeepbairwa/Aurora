package com.kuldeep.aurora.features.login.domain

import com.kuldeep.aurora.core.domain.repository.DataStoreRepository
import javax.inject.Inject

class SetUserPhoneUseCase @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) {
    suspend operator fun invoke(phone:String){
        dataStoreRepository.setUserPhone(phone)
    }
}