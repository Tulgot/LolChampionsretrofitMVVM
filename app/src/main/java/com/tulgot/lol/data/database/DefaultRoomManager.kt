package com.tulgot.lol.data.database

import com.tulgot.lol.data.database.dao.ChampionDao
import com.tulgot.lol.data.database.entities.PassiveEntity
import com.tulgot.lol.data.database.entities.SpellEntity
import com.tulgot.lol.domain.model.Champion
import com.tulgot.lol.domain.model.Passive
import com.tulgot.lol.domain.model.Spell
import com.tulgot.lol.domain.room.RoomManager
import javax.inject.Inject

class DefaultRoomManager @Inject constructor(
    private val championDao: ChampionDao
) : RoomManager {

    override suspend fun getAllChampions() = championDao.getAllChampions().map {
        it.toChampionRoom()
    }

    override suspend fun insertChampionDetail(champion: Champion) {
        val championToChampionEntity = champion.toChampionEntity()
        championDao.insertChampionDetail(championToChampionEntity)
    }

    override suspend fun getChampionById(id: String) = championDao.getChampionById(id).map {
        it.toChampionRoom()
    }

    override suspend fun insertPassive(passive: Passive, id: String) {
        val passiveToEntity = PassiveEntity(
            championid = id,
            description = passive.description.toString(),
            name = passive.name.toString(),
            image = passive.image?.full.toString()
        )
        championDao.insertPassive(passiveToEntity)
    }

    override suspend fun getPassiveByChampionName(name: String) =
        championDao.getPassiveByChampionName(name).toPassiveRoom()


    override suspend fun insertSpell(spell: List<Spell>, id: String) {
        val spellToEntity: List<SpellEntity> = spell.map {
            it.toSpellEntity(id)
        }
        championDao.insertSpell(spellToEntity)
    }

    override suspend fun getSpellByChampionName(name: String) =
        championDao.getSpellByChampionName(name).map {
            it.toSpellRoom()
        }

    override suspend fun deleteChampionDetail(id: String) {
        championDao.deleteChampionDetail(id)
    }

    override suspend fun deleteAllChampions() {
        championDao.deleteAllChampions()
    }

    override suspend fun deleteAllSpells() {
        championDao.deleteAllSpells()
    }

    override suspend fun deleteAllPassives() {
        championDao.deleteAllPassives()
    }

    override suspend fun updateChampionImage(url: String, championId: String) {
        championDao.updateChampionImage(url, championId)
    }


}