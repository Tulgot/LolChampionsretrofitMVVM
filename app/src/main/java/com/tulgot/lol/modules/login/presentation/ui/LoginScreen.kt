package com.tulgot.lol.modules.login.presentation.ui

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.firebase.auth.FirebaseAuth
import com.tulgot.lol.R
import com.tulgot.lol.core.components.EditTextTopLabel

@Composable
fun LoginScreen(context: Context) {

    var email by rememberSaveable { mutableStateOf("") }
    var psw by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp)
    ) {
        Spacer(Modifier.height(40.dp))
        Text(text = "User:",
            Modifier
                .fillMaxWidth(0.80f)
                .align(Alignment.CenterHorizontally), style = TextStyle(
            fontSize = 24.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        ))
        EditTextTopLabel(onValueChange = {
            email = it
        }, value = email, modifier = Modifier
            .fillMaxWidth(0.80f)
            .align(Alignment.CenterHorizontally))
        Spacer(Modifier.height(20.dp))
        Text("Password:",
            Modifier
                .fillMaxWidth(0.80f)
                .align(Alignment.CenterHorizontally), style = TextStyle(
                fontSize = 24.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            ))
        EditTextTopLabel(onValueChange = {
            psw = it
        }, value = psw, modifier = Modifier
            .fillMaxWidth(0.80f)
            .align(Alignment.CenterHorizontally))


        Button(modifier = Modifier
            .padding(top = 50.dp)
            .fillMaxWidth(0.80f)
            .align(Alignment.CenterHorizontally)
            .height(50.dp),
            onClick = {

            }) {
            Text("Iniciar sesion")
        }

        Button(modifier = Modifier
            .padding(top = 30.dp)
            .fillMaxWidth(0.80f)
            .align(Alignment.CenterHorizontally)
            .height(50.dp),
            onClick = {
               val googleIdOption = GetGoogleIdOption.Builder()
                    .setFilterByAuthorizedAccounts(true)
                    .setServerClientId(R.string.default_web_client_id.toString())//TODO fix here
                    .build()

                val request = GetCredentialRequest.Builder()
                    .addCredentialOption(googleIdOption)
                    .build()





               /*val request = GetCredentialRequest.Builder()
                    .addCredentialOption(
                        GetGoogleIdOption.Builder().setFilterByAuthorizedAccounts(false)
                            .setServerClientId(
                                R.string.default_web_client_id.toString()
                            ).setAutoSelectEnabled(false).build()
                    ).build()*/



            }) {
            Text("Iniciar sesion con Google")
            Spacer(Modifier.width(10.dp))
            Image(
                modifier = Modifier
                    .size(40.dp),
                painter = painterResource(id = R.drawable.icon_google),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                )
        }
    }

}

@Preview(showSystemUi = true)
@Composable
fun LoginScreenPreview() {
}