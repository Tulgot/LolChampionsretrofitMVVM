package com.tulgot.lol.di

import com.tulgot.lol.modules.login.data.RemoteLoginDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataSourceModule {

    @Provides
    @Singleton
    fun provideRemoteLoginDataSource() =
        RemoteLoginDataSource()

}