package com.tulgot.lol.modules.login.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.tulgot.lol.core.components.EditTextTopLabel

@Composable
fun LoginScreen() {
    var email by rememberSaveable { mutableStateOf("") }
    var psw by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(top = 50.dp)
    ) {
        EditTextTopLabel(onValueChange = {
            email = it
        }, value = email)

        EditTextTopLabel(onValueChange = {
            psw = it
        }, value = psw)

        Button(modifier = Modifier.padding(top = 50.dp),
            onClick = {
                val googleIdOption = GetGoogleIdOption.Builder()
                    .setFilterByAuthorizedAccounts(true)
                    .setServerClientId(baseContext.getString(R.string.default_web_client_id))//TODO fix here
                    .build()

                val request = GetCredentialRequest.Builder()
                    .addCredentialOption(googleIdOption)
                    .build()
        }) {
            Text("Iniciar sesion")
        }
    }

}

@Preview(showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}