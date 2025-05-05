package com.tulgot.lol.modules.login.presentation.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthResult
import com.tulgot.lol.modules.login.domain.LoginManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

const val TAG = "LoginErrorMessage"

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginManager: LoginManager,
) : ViewModel() {

    var authResult = mutableStateListOf<AuthResult?>()
        private set

    /*var isSignIn = mutableStateOf(false)
        private set

    init {
        viewModelScope.launch {
            isSignIn()
        }
    }*/


    fun start() {
        viewModelScope.launch(Dispatchers.IO) {
            loginManager.signInGoogle().collect {
                authResult.add(it)
            }
        }
    }

    /*suspend fun isSignIn(){
        if(Firebase.auth.getCurrentUser() != null) {
            isSignIn.value = true
        }
    }*/


    suspend fun signOut() {
        /*credentialManager.clearCredentialState(
            ClearCredentialStateRequest()
        )
        firebaseAuth.signOut()*/
    }

    fun logOut() {
        viewModelScope.launch(Dispatchers.IO) {
//            signOut()
        }
    }


}
