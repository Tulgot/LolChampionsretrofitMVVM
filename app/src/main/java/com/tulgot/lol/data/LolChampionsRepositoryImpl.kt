package com.tulgot.lol.data

import com.tulgot.lol.domain.LolChampionsRepository
import com.tulgot.lol.domain.model.ChampionResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LolChampionsRepositoryImpl @Inject constructor(
    private val lolApi: LolApi,
): LolChampionsRepository{

    override suspend fun getAllChampions(): Flow<ChampionResponse> {
        return flow {
            emit(
                lolApi.getChampionList().toChampionResponse()
            )
        }
    }

    override suspend fun getChampionDetails(name: String): Flow<ChampionResponse> {
        return flow {
            emit(
                lolApi.getChampionByName(name).toChampionResponse()
            )
        }
    }


}