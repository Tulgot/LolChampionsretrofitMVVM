package com.tulgot.lol.modules.login.domain

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import javax.inject.Inject

class FirebaseAuthProvider @Inject constructor(): AuthProvider {
    override fun currentUser(): FirebaseUser?{
        return Firebase.auth.currentUser
    }
}