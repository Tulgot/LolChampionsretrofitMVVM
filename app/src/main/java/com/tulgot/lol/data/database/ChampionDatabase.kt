package com.tulgot.lol.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tulgot.lol.data.database.dao.ChampionDao
import com.tulgot.lol.data.database.entities.ChampionEntity
import com.tulgot.lol.data.database.entities.PassiveEntity
import com.tulgot.lol.data.database.entities.SpellEntity

@Database(entities = [
    ChampionEntity::class,
    PassiveEntity::class,
    SpellEntity::class
                     ],
    version = 1, exportSchema = false)
abstract class ChampionDatabase: RoomDatabase() {

    abstract fun getChampionDao(): ChampionDao
}