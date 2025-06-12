package com.tulgot.lol.modules.firestore.data

import com.tulgot.lol.data.database.toChampionEntity
import com.tulgot.lol.data.database.toChampionRoom
import com.tulgot.lol.domain.model.Champion
import com.tulgot.lol.domain.room.model.ChampionRoom
import com.tulgot.lol.modules.firestore.domain.FireStoreManager
import javax.inject.Inject

class DefaultFireStoreManager @Inject constructor(
    private val remoteFireStoreDataSource: RemoteFireStoreDataSource
) : FireStoreManager {

    override suspend fun addFavoriteChampion(champion: Champion, uid: String) {
        val championToChampionRoom = champion.toChampionRoom()
        remoteFireStoreDataSource.addFavoriteChampion(championToChampionRoom, uid)
    }

    override suspend fun deleteFavoriteChampion(championId: String, uid: String) {
//        val championListToEntityList = championList.map {
//            it.toChampionEntity()
//        }
        remoteFireStoreDataSource.deleteFavoriteChampion(championId, uid)
    }

    override suspend fun getFavoriteByUser(uid: String): List<ChampionRoom> {
         return remoteFireStoreDataSource.getFavoriteByUser(uid)
    }
}