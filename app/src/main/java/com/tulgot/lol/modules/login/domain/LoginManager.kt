package com.tulgot.lol.modules.login.domain

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

interface LoginManager {

    suspend fun signInGoogle(): Flow<AuthResult?>
    suspend fun signOut()

    suspend fun signIn(user: String, password: String): Task<AuthResult?>
    suspend fun register(user: String, password: String): Task<AuthResult?>

}