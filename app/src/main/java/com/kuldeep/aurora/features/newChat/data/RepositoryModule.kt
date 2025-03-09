package com.kuldeep.aurora.features.newChat.data

import com.kuldeep.aurora.features.newChat.domain.repository.ContactsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindContactsRepository(impl: ContactsRepositoryImpl): ContactsRepository
}