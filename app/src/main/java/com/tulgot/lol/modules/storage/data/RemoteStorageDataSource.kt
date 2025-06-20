package com.tulgot.lol.modules.storage.data

import android.net.Uri
import android.util.Log
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import javax.inject.Inject

class RemoteStorageDataSource @Inject constructor() {

    private val storage = Firebase.storage.reference
    private var imagesRef = storage.child("images")

    suspend fun storeImage(uri: Uri, uid: String){
            imagesRef.child(uid).child(uri.toString().substringAfterLast("/")).putFile(uri).addOnSuccessListener { task ->
                task.metadata!!.reference!!.downloadUrl.addOnSuccessListener { url ->
//                    Log.i("storeImage", "storeImage success: $url")
                }
            }.addOnFailureListener { error ->
                Log.i("storeImage", "storeImage success: $error")
            }
    }
}