package com.tulgot.lol.domain.room

import com.tulgot.lol.domain.model.Champion
import com.tulgot.lol.domain.model.Passive
import com.tulgot.lol.domain.model.Spell
import com.tulgot.lol.domain.room.model.ChampionRoom
import com.tulgot.lol.domain.room.model.PassiveRoom
import com.tulgot.lol.domain.room.model.SpellRoom

interface RoomManager {

    suspend fun getAllChampions(): List<ChampionRoom>
    suspend fun insertChampionDetail(champion: Champion)
    suspend fun getChampionById(id: String): List<ChampionRoom>
    suspend fun insertPassive(passive: Passive, id: String)
    suspend fun getPassiveByChampionName(name: String): PassiveRoom
    suspend fun insertSpell(spell: List<Spell>, id: String)
    suspend fun getSpellByChampionName(name: String): List<SpellRoom>
    suspend fun deleteChampionDetail(id: String)
}