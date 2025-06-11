package com.tulgot.lol.modules.firestore.data

import com.tulgot.lol.data.database.toChampionEntity
import com.tulgot.lol.domain.model.Champion
import com.tulgot.lol.modules.firestore.domain.FireStoreManager
import javax.inject.Inject

class DefaultFireStoreManager @Inject constructor(
    private val remoteFireStoreDataSource: RemoteFireStoreDataSource
) : FireStoreManager {

    override suspend fun addFavoriteChampion(champion: Champion, uid: String){
        val championToChampionEntity = champion.toChampionEntity()
        remoteFireStoreDataSource.addFavoriteChampion(championToChampionEntity, uid)
    }
}