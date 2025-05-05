package com.tulgot.lol.modules.login.data

import android.content.Context
import android.util.Log
import androidx.credentials.Credential
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential.Companion.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.tulgot.lol.BuildConfig
import com.tulgot.lol.modules.login.presentation.ui.TAG
import dagger.Provides
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

class RemoteLoginDataSource @Inject constructor(
    val credentialManager: CredentialManager,
    val context: Context
) {

//    private val credentialManager = CredentialManager.create(context)

    suspend fun signIn() =
        try {
            handleSignIn(buildCredentialRequest(context))
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            Log.i(TAG, "signIn: ${e.message}")
            null
        }


    private suspend fun buildCredentialRequest(context: Context): Credential {
        val request = GetCredentialRequest.Builder()
            .addCredentialOption(
                GetGoogleIdOption.Builder()
                    .setFilterByAuthorizedAccounts(false)
                    .setServerClientId(
                        BuildConfig.AUTHENTICATION_WEB_CLIENT_ID
                    )
                    .setAutoSelectEnabled(false)
                    .build()
            )
            .build()

        val credential =
            credentialManager.getCredential(request = request, context = context).credential
        return credential
    }

    private suspend fun handleSignIn(credential: Credential): AuthResult? {
        if (credential is CustomCredential &&
            credential.type == TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
        ) {
            try {
                val tokenCredential = GoogleIdTokenCredential.createFrom(credential.data)

                Log.i(TAG, "name: ${tokenCredential.displayName}")
                Log.i(TAG, "email: ${tokenCredential.id}")
                Log.i(TAG, "image: ${tokenCredential.profilePictureUri}")

                val authCredential = GoogleAuthProvider
                    .getCredential(tokenCredential.idToken, null)

                val authResult =
                    FirebaseAuth.getInstance().signInWithCredential(authCredential).await()

                return authResult

            } catch (e: GoogleIdTokenParsingException) {
                Log.i(TAG, "GoogleIdTokenParsingException: ${e.message}")
                return null
            }
        } else {
            Log.w(TAG, "Credential is not of type Google ID!")
            return null
        }
    }

}