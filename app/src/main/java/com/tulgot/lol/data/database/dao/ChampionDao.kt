package com.tulgot.lol.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tulgot.lol.data.database.entities.ChampionEntity

@Dao
interface ChampionDao {

    @Query("SELECT * FROM champion_table ORDER BY ID DESC")
    fun getAllChampions(): List<ChampionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertChampionDetail(championDetail: ChampionEntity)

    @Query("SELECT * FROM champion_table WHERE id = :id")
    fun getChampionById(id: String): List<ChampionEntity>
}