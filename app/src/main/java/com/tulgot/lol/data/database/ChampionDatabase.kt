package com.tulgot.lol.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tulgot.lol.data.database.dao.ChampionDao
import com.tulgot.lol.data.database.entities.ChampionEntity

@Database(entities = [ChampionEntity::class], version = 1)
abstract class ChampionDatabase: RoomDatabase() {

    abstract fun getChampionDao(): ChampionDao
}