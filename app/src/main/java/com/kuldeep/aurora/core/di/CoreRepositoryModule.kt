package com.kuldeep.aurora.core.di

import com.kuldeep.aurora.core.data.repository.WebSocketRepository
import com.kuldeep.aurora.core.domain.repository.WebSocketRepoImpl
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
}