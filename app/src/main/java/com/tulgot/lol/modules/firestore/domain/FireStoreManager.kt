package com.tulgot.lol.modules.firestore.domain

import com.tulgot.lol.domain.model.Champion

interface FireStoreManager {

    suspend fun addFavoriteChampion(champion: Champion, uid: String)
}