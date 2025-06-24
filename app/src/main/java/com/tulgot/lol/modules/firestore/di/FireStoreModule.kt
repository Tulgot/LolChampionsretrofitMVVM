package com.tulgot.lol.modules.firestore.di

import com.tulgot.lol.modules.firestore.data.DefaultFireStoreManager
import com.tulgot.lol.modules.firestore.domain.FireStoreManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class FireStoreModule() {

    @Binds
    abstract fun bindFireStoreManager(defaultFireStoreManager: DefaultFireStoreManager): FireStoreManager
}