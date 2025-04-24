package com.tulgot.lol.di

import android.content.Context
import androidx.room.Room
import com.tulgot.lol.data.database.ChampionDatabase
import com.tulgot.lol.domain.CHAMPION_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, ChampionDatabase::class.java, CHAMPION_DATABASE).build()

    @Singleton
    @Provides
    fun provideDao(db: ChampionDatabase) = db.getChampionDao()
}