package com.tulgot.lol.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tulgot.lol.data.database.entities.ChampionEntity
import com.tulgot.lol.data.database.entities.PassiveEntity
import com.tulgot.lol.data.database.entities.SpellEntity

@Dao
interface ChampionDao {

    @Query("SELECT * FROM champion_table ORDER BY ID DESC")
    fun getAllChampions(): List<ChampionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertChampionDetail(championDetail: ChampionEntity)

    @Query("SELECT * FROM champion_table WHERE id = :id")
    fun getChampionById(id: String): List<ChampionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPassive(passiveEntity: PassiveEntity)

    @Query("SELECT * FROM passive_table WHERE championid = :name")
    fun getPassiveByChampionName(name: String): PassiveEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSpell(spellEntity: List<SpellEntity>)

    @Query("SELECT * FROM spell_table WHERE championid = :name")
    fun getSpellByChampionName(name: String): List<SpellEntity>

    @Query("DELETE FROM  champion_table WHERE id = :id")
    fun deleteChampionDetail(id: String)

}