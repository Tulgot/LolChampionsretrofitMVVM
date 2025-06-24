package com.tulgot.lol.modules.storage.data

import android.net.Uri
import com.tulgot.lol.modules.storage.domain.StorageManager
import javax.inject.Inject

class DefaultStorageManager @Inject constructor(
    private val remoteStorageDataSource: RemoteStorageDataSource
) : StorageManager {

    override suspend fun storeImage(uid: String, uri: Uri): String {
        return remoteStorageDataSource.storeImage(uri, uid)
    }

}