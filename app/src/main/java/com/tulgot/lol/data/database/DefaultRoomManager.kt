package com.tulgot.lol.data.database

import com.tulgot.lol.data.database.dao.ChampionDao
import com.tulgot.lol.domain.model.Champion
import com.tulgot.lol.domain.room.ChampionRoom
import com.tulgot.lol.domain.room.RoomManager
import javax.inject.Inject

class DefaultRoomManager @Inject constructor(
    private val championDao: ChampionDao
) : RoomManager {

    override suspend fun getAllChampions(): List<ChampionRoom> {
        return championDao.getAllChampions().map {
            it.toChampionRoom()
        }
    }

    override suspend fun insertChampionDetail(champion: Champion) {
        val xyz = champion.toChampionEntity()
        championDao.insertChampionDetail(xyz)
    }

    override suspend fun getChampionById(id: String): List<ChampionRoom> {
        return championDao.getChampionById(id).map {
            it.toChampionRoom()
        }
    }

}