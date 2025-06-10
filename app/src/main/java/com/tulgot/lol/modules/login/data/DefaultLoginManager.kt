package com.tulgot.lol.modules.login.data

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.tulgot.lol.modules.login.domain.LoginManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class DefaultLoginManager @Inject constructor(
    private val remoteLoginDataSource: RemoteLoginDataSource
) : LoginManager {


    override suspend fun signInGoogle(): Flow<AuthResult?> {
        return flow {
            emit(
                remoteLoginDataSource.signInGoogle()
            )
        }
    }

    override suspend fun signOut() {
        remoteLoginDataSource.signOut()
    }

    override suspend fun signIn(user: String, password: String): Task<AuthResult?> {
        return remoteLoginDataSource.signIn(user, password)
    }

    override suspend fun register(user: String, password: String): Task<AuthResult?> {
        return remoteLoginDataSource.register(user, password)
    }


}