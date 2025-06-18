package com.tulgot.lol.domain.network.internetconnectionobserver.domain

import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {
    val isConnected: Flow<Boolean>
    fun connectionCheck(): Boolean
}