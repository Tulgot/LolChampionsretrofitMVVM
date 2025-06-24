package com.tulgot.lol.modules.firestore.data

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.gson.annotations.SerializedName
import com.tulgot.lol.domain.room.model.ChampionRoom
import com.tulgot.lol.domain.room.model.PassiveRoom
import com.tulgot.lol.domain.room.model.SpellRoom
import com.tulgot.lol.modules.login.presentation.ui.TAG
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

class RemoteFireStoreDataSource @Inject constructor() {

    private val fireStoreDB: FirebaseFirestore = Firebase.firestore

    suspend fun addFavoriteChampion(
        champion: ChampionRoom,
        uid: String,
        passive: PassiveRoom,
        spells: List<SpellRoom>
    ) {
        try {

            fireStoreDB.collection("Users")
                .document(uid)
                .collection("favorites")
                .add(champion)


            fireStoreDB.collection("Users")
                .document(uid)
                .collection("passive")
                .add(passive)

            for (spellRoom in spells) {
                fireStoreDB.collection("Users")
                    .document(uid)
                    .collection("spells")
                    .add(spellRoom)
                    .await()
            }


        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            Log.i(TAG, "addToFireStore: ${e.message}")
        }
    }

    suspend fun deleteFavoriteChampion(championId: String, uid: String) {
        try {
            val favoriteChampionById = fireStoreDB
                .collection("Users")
                .document(uid)
                .collection("favorites").get().await()

            val favoritePassiveById = fireStoreDB
                .collection("Users")
                .document(uid)
                .collection("passive").get().await()

            val favoriteSpellById = fireStoreDB
                .collection("Users")
                .document(uid)
                .collection("spells").get().await()



            for (document in favoriteChampionById) {
                if (document.data.values.contains(championId)) {
                    fireStoreDB
                        .collection("Users")
                        .document(uid)
                        .collection("favorites").document(document.id).delete()
                }
            }

            for (document in favoritePassiveById) {
                if (document.data.values.contains(championId)) {
                    fireStoreDB
                        .collection("Users")
                        .document(uid)
                        .collection("passive").document(document.id).delete()
                }
            }

            for (document in favoriteSpellById) {
                if (document.data.values.contains(championId)) {
                    fireStoreDB
                        .collection("Users")
                        .document(uid)
                        .collection("spells").document(document.id).delete()
                }
            }


        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            Log.i(TAG, "addToFireStore: ${e.message}")
        }

    }

    data class FavoriteFireStoreByUser(
        @SerializedName("championList") val championList: List<ChampionRoom>,
        @SerializedName("passiveList") val passiveList: List<PassiveRoom>,
        @SerializedName("spellList") val spellList: List<SpellRoom>,
    )

    suspend fun getFavoriteByUser(uid: String): FavoriteFireStoreByUser {
        try {
            val championsList = fireStoreDB
                .collection("Users")
                .document(uid)
                .collection("favorites")
                .get()
                .await().documents.mapNotNull { snapshot ->
                    snapshot.toObject(ChampionRoom::class.java)
                }

            val passivesList = fireStoreDB
                .collection("Users")
                .document(uid)
                .collection("passive")
                .get()
                .await().documents.mapNotNull { snapshot ->
                    snapshot.toObject(PassiveRoom::class.java)
                }

            val spellsList = fireStoreDB
                .collection("Users")
                .document(uid)
                .collection("spells")
                .get()
                .await().documents.mapNotNull { snapshot ->
                    snapshot.toObject(SpellRoom::class.java)
                }

            val allChampions = FavoriteFireStoreByUser(championsList, passivesList, spellsList)
            return allChampions
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            Log.i(TAG, "getFavoriteByUser: ${e.message}")
            return FavoriteFireStoreByUser(emptyList(), emptyList(), emptyList())
        }

    }

    suspend fun updateFavoriteImage(url: String, championId: String, uid: String) {
        try {
            val favoriteChampionById = fireStoreDB
                .collection("Users")
                .document(uid)
                .collection("favorites").get().await()

            for (document in favoriteChampionById) {
                if (document.data.values.contains(championId)) {
                    fireStoreDB.collection("Users")
                        .document(uid).collection("favorites")
                        .document(document.id).update("image", url)
                }
            }

        } catch (e: Exception) {
            throw e

        }
    }
}