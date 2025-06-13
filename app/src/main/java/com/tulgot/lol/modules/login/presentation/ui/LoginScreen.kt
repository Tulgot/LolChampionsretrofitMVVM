package com.tulgot.lol.modules.login.presentation.ui

import android.widget.Toast
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tulgot.lol.R
import com.tulgot.lol.core.components.EditTextTopLabel


@Composable
fun LoginScreen(
    navigateToChampionList: () -> Unit,
    navigateToRegistration: () -> Unit,
    loginViewModel: LoginViewModel = hiltViewModel()
) {

    var email by rememberSaveable { mutableStateOf("") }
    var psw by rememberSaveable { mutableStateOf("") }
    val context = LocalContext.current

    var emailMessage by remember { mutableStateOf(false) }
    var pswMessage by remember { mutableStateOf(false) }

    Box(Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .fillMaxHeight()
                .padding(top = 50.dp)
                .align(Alignment.Center),
        ) {
            welcome("Bienvenido!")
            Spacer(Modifier.height(40.dp))
            Titles("Correo: ", email, false) { email = it }
            Spacer(Modifier.height(20.dp))
            Titles("Contraseña: ", psw, true) { psw = it }


            Button(modifier = Modifier
                .padding(top = 50.dp)
                .height(50.dp)
                .fillMaxWidth(),
                enabled = if ( email.isNotEmpty() && psw.isNotEmpty() )true else false,
                onClick = {
                    loginViewModel.signIn(email, psw,
                        success = {
                            navigateToChampionList()
                        },
                        fail = {
                            Toast.makeText(context, "No existe usuario", Toast.LENGTH_SHORT).show()
                        },
                        failEmailValidation = {
                            emailMessage = true
                        },
                        failPswValidation = {
                            pswMessage = true
                        })
                }) {
                Text("Iniciar Sesión")
            }

            Button(modifier = Modifier
                .padding(top = 30.dp)
                .height(50.dp)
                .fillMaxWidth(),
                onClick = {
                    navigateToRegistration()
                }) {
                Text("Registrarse")
                Spacer(Modifier.width(10.dp))
            }

            Button(modifier = Modifier
                .padding(top = 30.dp)
                .height(50.dp)
                .fillMaxWidth(),

                onClick = {
                    loginViewModel.start(
                        success = navigateToChampionList
                    )
                }) {
                Text("Iniciar sesión con Google")
                Spacer(Modifier.width(10.dp))
                Image(
                    modifier = Modifier
                        .size(40.dp),
                    painter = painterResource(id = R.drawable.icon_google),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                )
            }

            Spacer(
                Modifier
                    .fillMaxWidth()
                    .height(10.dp))

            if (emailMessage) {
                failValidation("Verificar el correo")
            }

            if (pswMessage) {
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

@Composable
fun Titles(title: String, value: String, editText: Boolean, onValueChange: (String) -> Unit) {
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
            value = value, onValueChange = onValueChange,
            isPassword = editText,
            keyboardOptions = if(editText){
                KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password)
            }else{
                KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email)
            }
        )
    }
}

@Composable
fun welcome(welcome: String) {
    Text(
        text = welcome,
        style = TextStyle(
            fontSize = 26.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
    )
}

@Composable
fun failValidation(messege: String){
    Text(
        text = messege,
        style = TextStyle(
            fontSize = 16.sp,
            color = Color.Gray,
    )
    )
}