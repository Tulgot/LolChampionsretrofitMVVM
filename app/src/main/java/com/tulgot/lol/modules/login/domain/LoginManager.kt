package com.tulgot.lol.modules.login.domain

import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

interface LoginManager {

    suspend fun signInGoogle(): Flow<AuthResult?>

}