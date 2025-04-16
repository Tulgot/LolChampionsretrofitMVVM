package com.tulgot.lol.domain

import com.tulgot.lol.domain.model.ChampionResponse
import kotlinx.coroutines.flow.Flow

interface LolChampionsRepository {

    suspend fun getAllChampions(): Flow<ChampionResponse>

    suspend fun getChampionDetails(name: String): Flow<ChampionResponse>
}