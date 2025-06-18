package com.tulgot.lol.modules.firestore.domain

import com.tulgot.lol.domain.model.Champion
import com.tulgot.lol.modules.firestore.data.RemoteFireStoreDataSource.FavoriteFireStoreByUser

interface FireStoreManager {

    suspend fun addFavoriteChampion(champion: Champion, uid: String)
    suspend fun deleteFavoriteChampion(championId: String, uid: String)
    suspend fun getFavoriteByUser(uid: String): FavoriteFireStoreByUser
}