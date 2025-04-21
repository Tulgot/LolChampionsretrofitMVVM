package com.tulgot.lol.domain.datastore

interface DataStoreManager {

    suspend fun putSettings(key: String, value: Boolean)
    suspend fun getSettings(key: String): Boolean?

    suspend fun putDeviceCheckBox(key: String, value: Boolean)
    suspend fun getDeviceCheckBox(key: String): Boolean?

}