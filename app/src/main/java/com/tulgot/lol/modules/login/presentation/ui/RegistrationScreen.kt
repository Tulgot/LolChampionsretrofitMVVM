package com.tulgot.lol.modules.login.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun RegistrationScreen(
    navToLogin: () -> Unit,
    navToChampionList: () -> Unit,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    var email by rememberSaveable { mutableStateOf("") }
    var psw by rememberSaveable { mutableStateOf("") }

    var emailMessege by remember { mutableStateOf(false) }
    var pswMessege by remember { mutableStateOf(false) }

    Box(Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.padding(top = 40.dp),
        ) {
            IconButton(
                onClick = {
                    navToLogin()
                },
            ) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBackIosNew,
                    contentDescription = ""
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .wrapContentHeight()
                .padding(top = 50.dp)
                .align(Alignment.TopCenter),
        ) {
            welcome("Registrarse")
            Spacer(Modifier.height(40.dp))
            Titles("Correo: ", email, false) { email = it }
            Spacer(Modifier.height(20.dp))
            Titles("Contraseña: ", psw, true) { psw = it }

            Button(modifier = Modifier
                .padding(top = 30.dp)
                .height(50.dp)
                .fillMaxWidth(),
                enabled = if (email.isNotEmpty() && psw.isNotEmpty()) true else false,
                onClick = {
                    loginViewModel.register(email, psw,
                        success = {
                            navToChampionList()
                        },
                        failEmailValidation = {
                            emailMessege = true
                        },
                        failPswValidation = {
                            pswMessege = true
                        })
                }) {
                Text("Registrarse")
            }

            Spacer(Modifier.height(20.dp))
            if (emailMessege) {
                failValidation("Verificar el correo")
            }

            if (pswMessege) {
                failValidation(
                    "La contraseña debe contar \n" +
                            "con almenos una letra mayuscula \n" +
                            "una letra minuscula \n" +
                            "un numero \n" +
                            "y con algun caraceter especial \n" +
                            "y ser de almenos 6 caracteres"
                )
            }

        }
    }

}

