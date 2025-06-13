package com.tulgot.lol.modules.firestore.data

import com.tulgot.lol.data.database.toChampionRoom
import com.tulgot.lol.data.database.toSpellRoom
import com.tulgot.lol.domain.model.Champion
import com.tulgot.lol.domain.room.model.PassiveRoom
import com.tulgot.lol.domain.room.model.SpellRoom
import com.tulgot.lol.modules.firestore.data.RemoteFireStoreDataSource.FavoriteFirestoreByUser
import com.tulgot.lol.modules.firestore.domain.FireStoreManager
import javax.inject.Inject

class DefaultFireStoreManager @Inject constructor(
    private val remoteFireStoreDataSource: RemoteFireStoreDataSource
) : FireStoreManager {

    override suspend fun addFavoriteChampion(champion: Champion, uid: String) {
        val championToChampionRoom = champion.toChampionRoom()
        val passive = PassiveRoom(
            image = champion.passive?.image?.full.toString(),
            championid = champion.id.toString(),
            name = champion.passive?.name.toString(),
            description = champion.passive?.description.toString()
        )
        val spell: List<SpellRoom> = champion.spells!!.map {
            it.toSpellRoom(champion.id.toString())
        }
        remoteFireStoreDataSource.addFavoriteChampion(championToChampionRoom, uid, passive, spell)
    }

    override suspend fun deleteFavoriteChampion(championId: String, uid: String) {
        remoteFireStoreDataSource.deleteFavoriteChampion(championId, uid)
    }

    override suspend fun getFavoriteByUser(uid: String): FavoriteFirestoreByUser {
        return remoteFireStoreDataSource.getFavoriteByUser(uid)
    }
}