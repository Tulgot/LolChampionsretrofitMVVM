package com.tulgot.lol.di

import com.tulgot.lol.data.LolChampionsRepositoryImpl
import com.tulgot.lol.data.database.DefaultRoomManager
import com.tulgot.lol.domain.LolChampionsRepository
import com.tulgot.lol.domain.room.RoomManager
import com.tulgot.lol.modules.firestore.data.DefaultFireStoreManager
import com.tulgot.lol.modules.firestore.domain.FireStoreManager
import com.tulgot.lol.modules.login.data.DefaultLoginManager
import com.tulgot.lol.modules.login.domain.LoginManager
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

    @Binds
    @Singleton
    abstract fun bindRoomManager(defaultRoomManager: DefaultRoomManager): RoomManager

    @Binds
    abstract fun bindLoginManager(defaultLoginManager: DefaultLoginManager): LoginManager

    @Binds
    abstract fun bindFireStoreManager(defaultFireStoreManager: DefaultFireStoreManager): FireStoreManager

}