package com.tulgot.lol.domain.room

import com.tulgot.lol.domain.model.Champion

interface RoomManager {

    suspend fun getAllChampions(): List<ChampionRoom>
    suspend fun insertChampionDetail(champion: Champion)
    suspend fun getChampionById(id: String): List<ChampionRoom>
}