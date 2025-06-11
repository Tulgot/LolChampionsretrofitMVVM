package com.tulgot.lol.modules.firestore.data

import android.util.Log
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.tulgot.lol.data.database.entities.ChampionEntity
import com.tulgot.lol.modules.login.presentation.ui.TAG
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

class RemoteFireStoreDataSource @Inject constructor() {

    private val fireStoreDB: FirebaseFirestore = Firebase.firestore

    suspend fun addFavoriteChampion(champion: ChampionEntity, uid: String){
        try {
            fireStoreDB.collection("Users").document(uid)
                .update("favorites", FieldValue.arrayUnion(champion)).await()
        }catch (e: Exception){
            e.printStackTrace()
            if (e is CancellationException) throw e
            Log.i(TAG, "addToFireStore: ${e.message}")
        }
    }
}