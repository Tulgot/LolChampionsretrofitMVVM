package com.tulgot.lol.domain.room

import com.tulgot.lol.domain.model.Champion
import com.tulgot.lol.domain.model.Passive
import com.tulgot.lol.domain.model.Spell

interface RoomManager {

    suspend fun getAllChampions(): List<ChampionRoom>
    suspend fun insertChampionDetail(champion: Champion)
    suspend fun getChampionById(id: String): List<ChampionRoom>
    suspend fun insertPassive(passive: Passive, id: String)
    suspend fun getPassiveByChampionName(name: String): PassiveRoom
    suspend fun insertSpell(spell: List<Spell>, id: String)
    suspend fun getSpellByChampionName(name: String): List<SpellRoom>
}