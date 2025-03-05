package com.kuldeep.aurora.core.di

import com.kuldeep.aurora.core.data.repository.DataStoreRepoImpl
import com.kuldeep.aurora.core.domain.repository.WebSocketRepository
import com.kuldeep.aurora.core.data.repository.WebSocketRepoImpl
import com.kuldeep.aurora.core.domain.repository.DataStoreRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class CoreRepositoryModule {

    @Singleton
    @Binds
    abstract fun bindCoreRepository(impl: WebSocketRepoImpl): WebSocketRepository

    @Singleton
    @Binds
    abstract fun bindsDataStoreRepository(impl:DataStoreRepoImpl):DataStoreRepository
}