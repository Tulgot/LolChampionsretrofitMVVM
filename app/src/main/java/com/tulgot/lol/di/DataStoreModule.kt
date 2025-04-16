package com.tulgot.lol.di

import android.content.Context
import com.tulgot.lol.data.datastore.DefaultDataStoreManager
import com.tulgot.lol.domain.datastore.DataStoreManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Singleton
    @Provides
    fun providesDataStoreManager(@ApplicationContext context: Context)
    : DataStoreManager = DefaultDataStoreManager(context)
}