package com.tulgot.lol.modules.storage.data

import android.net.Uri
import android.util.Log
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class RemoteStorageDataSource @Inject constructor() {

    private val storage = Firebase.storage.reference
    private var imagesRef = storage.child("images")

    suspend fun storeImage(uri: Uri, uid: String){
            imagesRef.child(uid).putFile(uri).addOnSuccessListener { task ->
                task.metadata!!.reference!!.downloadUrl.addOnSuccessListener { url ->
                    Log.i("storeImage", "storeImage success: $url")
                }
            }

       val imageUrl = imagesRef.child(uid).putFile(uri).await().metadata!!.reference!!.downloadUrl.await()
        Log.i("storeImage", "storeImage await: $imageUrl")
    }
}