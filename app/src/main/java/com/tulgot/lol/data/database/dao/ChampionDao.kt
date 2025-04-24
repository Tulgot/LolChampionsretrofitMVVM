package com.tulgot.lol.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tulgot.lol.data.database.entities.ChampionEntity
import com.tulgot.lol.domain.room.ChampionRoom

@Dao
interface ChampionDao {

    @Query("SELECT * FROM champion_table ORDER BY ID DESC")
    fun getAllChampions(): List<ChampionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertChampionDetail(championDetail: ChampionEntity)

    //@Query("SELECT * FROM champion_detail WHERE id = :id")
    //suspend fun getChampionById(id: String): List<ChampionEntity>
}