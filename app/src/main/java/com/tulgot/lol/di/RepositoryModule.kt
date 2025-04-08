package com.tulgot.lol.di

import com.tulgot.lol.data.LolChampionsRepositoryImpl
import com.tulgot.lol.domain.LolChampionsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindLolChampionsRepository(lolChampionsRepositoryImpl: LolChampionsRepositoryImpl)
    : LolChampionsRepository
}