package com.tulgot.lol.modules.login.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.firebase.auth.FirebaseAuth
import com.tulgot.lol.R
import com.tulgot.lol.core.components.EditTextTopLabel

@Composable
fun LoginScreen(
    navigateToChampionList: () -> Unit,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        if (FirebaseAuth.getInstance().currentUser != null)
            navigateToChampionList()
    }

    var email by rememberSaveable { mutableStateOf("") }
    var psw by rememberSaveable { mutableStateOf("") }

    Box(Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .fillMaxHeight()
                .padding(top = 50.dp)
                .align(Alignment.Center),
        ) {
            Spacer(Modifier.height(40.dp))
            Titles("User: ", email) { email = it }
            Spacer(Modifier.height(20.dp))
            Titles("Password: ", psw) { psw = it }


            Button(modifier = Modifier
                .padding(top = 50.dp)
                .height(50.dp)
                .fillMaxWidth(),
                onClick = {
                    loginViewModel.start()
                }) {
                Text("Iniciar sesion")
            }

            Button(modifier = Modifier
                .padding(top = 30.dp)
                .height(50.dp)
                .fillMaxWidth(),
                onClick = {
                    loginViewModel.start()
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

}

@Composable
fun Titles(title: String, value: String, onValueChange: (String) -> Unit) {
    Column(Modifier.wrapContentHeight()) {
        Text(
            text = title,
            style = TextStyle(
                fontSize = 24.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        )
        EditTextTopLabel(
            value = value, onValueChange = onValueChange
        )
    }
}