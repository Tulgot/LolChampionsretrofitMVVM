package com.tulgot.lol.modules.storage.di

import com.tulgot.lol.modules.storage.data.DefaultStorageManager
import com.tulgot.lol.modules.storage.domain.StorageManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class StorageModule {

    @Binds
    abstract fun bindStorageManager(defaultStorageManager: DefaultStorageManager)
            : StorageManager

}