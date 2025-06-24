package com.tulgot.lol.modules.storage.data

import android.net.Uri
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteStorageDataSource @Inject constructor() {

    private val storage = Firebase.storage.reference
    private val imagesRef = storage.child("images")
    private lateinit var url: String

    suspend fun storeImage(uri: Uri, uid: String): String {

        withContext(Dispatchers.IO) {
            val task =
                imagesRef.child(uid).child(uri.toString().substringAfterLast("/")).putFile(uri)
                    .await()
            task.metadata?.reference?.downloadUrl?.addOnSuccessListener {
                url = it.toString()
            }?.await()
        }
        delay(5000L)

        return withContext(Dispatchers.IO) {
            url
        }
    }

}