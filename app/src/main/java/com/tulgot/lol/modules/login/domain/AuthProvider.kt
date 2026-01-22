package com.tulgot.lol.modules.login.domain

import com.google.firebase.auth.FirebaseUser

interface AuthProvider {
    fun currentUser(): FirebaseUser?
}