package com.tulgot.lol.data

import com.tulgot.lol.domain.LolChampionsRepository
import com.tulgot.lol.domain.model.ChampionResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LolChampionsRepositoryImpl @Inject constructor(
    private val lolApi: LolApi
): LolChampionsRepository{
    override suspend fun getAllChampions(): Flow<ChampionResponse> {
        TODO("Not yet implemented")
    }

}