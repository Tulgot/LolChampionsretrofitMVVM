package com.tulgot.lol.modules.storage.domain

import android.net.Uri

interface StorageManager {

    suspend fun storeImage(uid: String, uri: Uri): String
}

