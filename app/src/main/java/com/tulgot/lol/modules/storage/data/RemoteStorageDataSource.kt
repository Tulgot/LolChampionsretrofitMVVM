package com.tulgot.lol.modules.storage.data

import android.net.Uri
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import javax.inject.Inject

class RemoteStorageDataSource @Inject constructor() {

    private val storage = Firebase.storage.reference
    private val imagesRef = storage.child("images")
    private lateinit var url: String

    suspend fun storeImage(uri: Uri, uid: String, addOnSuccessListener: (String) -> Unit) {

        imagesRef.child(uid).child(uri.toString().substringAfterLast("/")).putFile(uri)
            .addOnSuccessListener { task ->
                task.metadata?.reference?.downloadUrl?.addOnSuccessListener {
                    addOnSuccessListener(it.toString())
                }
            }
    }


}