package com.tulgot.lol.modules.firestore.data

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.tulgot.lol.domain.room.model.ChampionRoom
import com.tulgot.lol.modules.login.presentation.ui.TAG
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

class RemoteFireStoreDataSource @Inject constructor() {

    private val fireStoreDB: FirebaseFirestore = Firebase.firestore

    suspend fun addFavoriteChampion(champion: ChampionRoom, uid: String) {
        try {
            fireStoreDB.collection("Users")
                .document(uid)
                .collection("favorites")
                .add(champion)
                .await()
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

            for (document in favoriteChampionById) {
                if (document.data.values.contains(championId)) {
                    fireStoreDB
                        .collection("Users")
                        .document(uid)
                        .collection("favorites").document(document.id).delete()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            Log.i(TAG, "addToFireStore: ${e.message}")
        }

    }

    suspend fun getFavoriteByUser(uid: String): List<ChampionRoom> {
        return try {
            fireStoreDB
                .collection("Users")
                .document(uid)
                .collection("favorites")
                .get()
                .await().documents.mapNotNull { snapshot ->
                    snapshot.toObject(ChampionRoom::class.java)
                }
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            Log.i(TAG, "addToFireStore: ${e.message}")
            emptyList()
        }

    }
}