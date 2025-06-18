package com.tulgot.lol.modules.login.presentation.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthResult
import com.tulgot.lol.domain.network.internetconnectionobserver.domain.ConnectivityObserver
import com.tulgot.lol.domain.room.RoomManager
import com.tulgot.lol.modules.login.domain.LoginManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

const val TAG = "LoginErrorMessage"

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginManager: LoginManager,
    private val roomManager: RoomManager,
    private val connectivityObserver: ConnectivityObserver
) : ViewModel() {

    var authResult = mutableStateListOf<AuthResult?>()
        private set

    val isConnected = connectivityObserver.isConnected.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        false
    )


    fun start(success: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.IO) {
                loginManager.signInGoogle().collect {
                    authResult.add(it)
                    withContext(Dispatchers.Main) { if (it != null) success() }

                }
            }
        }
    }


    fun logOut(success: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            roomManager.deleteAllChampions()
            roomManager.deleteAllPassives()
            roomManager.deleteAllSpells()
            withContext(Dispatchers.Main) {
                loginManager.signOut()
                success()
            }
        }
    }

    fun signIn(
        user: String,
        password: String,
        success: () -> Unit,
        fail: () -> Unit,
        failEmailValidation: () -> Unit,
        failPswValidation: () -> Unit,
    ) {

        val emailValidation = emailValidation(user)
        val pswValidation = pswValidation(password)

        if (emailValidation && pswValidation) {
            viewModelScope.launch(Dispatchers.IO) {
                loginManager.signIn(user, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        success()
                    } else {
                        fail()
                    }
                }
            }
        } else {
            if (!emailValidation) {
                failEmailValidation()
            }
            if (!pswValidation) {
                failPswValidation()
            }
        }
    }

    fun register(
        user: String,
        password: String,
        success: () -> Unit,
        failEmailValidation: () -> Unit,
        failPswValidation: () -> Unit,
    ) {
        val emailValidation = emailValidation(user)
        val pswValidation = pswValidation(password)

        if (emailValidation && pswValidation) {
            viewModelScope.launch(Dispatchers.IO) {
                loginManager.register(user, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        success()
                    }
                }
            }
        } else {
            if (!emailValidation) {
                failEmailValidation()
            }
            if (!pswValidation) {
                failPswValidation()
            }
        }
    }

    private fun emailValidation(email: String): Boolean {
        val emailpattern = Regex(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
        )
        return emailpattern.containsMatchIn(email)
    }

    private fun pswValidation(psw: String): Boolean {
        val pswPattern = Regex(
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=.])(?=\\S+$).{4,}$"
        )
        return pswPattern.containsMatchIn(psw)
    }

}
