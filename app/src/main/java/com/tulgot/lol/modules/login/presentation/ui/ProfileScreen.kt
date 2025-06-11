package com.tulgot.lol.modules.login.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.tulgot.lol.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navigateToLogIn: () -> Unit,
    loginViewModel: LoginViewModel = hiltViewModel()
) {

    val user = Firebase.auth.currentUser

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(user?.displayName.toString())
                }
            )
        }
    ) { innerPadding ->

        Box(
            Modifier
                .padding(top = innerPadding.calculateTopPadding())
                .fillMaxSize(),

            ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .align(Alignment.TopCenter)
                    .padding(top = 18.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                AsyncImage(
                    model = user?.photoUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .clip(CircleShape)
                        .height(100.dp)
                )

                if (user?.displayName != null) {
                    TextShow(user?.displayName.toString())
                }

                TextShow(user?.email.toString())

                Button(
                    onClick = {
                        loginViewModel.logOut(
                            success = navigateToLogIn
                        )
                    },
                    Modifier
                        .fillMaxWidth(.8f)
                        .align(Alignment.CenterHorizontally)
                ) {
                    TextShow("Sign Out")
                }

            }
        }

    }
}


@Composable
fun TextShow(string: String) {
    Text(
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold, text = string
    )
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun Preview() {
    Box(
        Modifier
            .fillMaxSize(),

        ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .align(Alignment.TopCenter)
                .padding(top = 18.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)


        ) {
            Row {
                Image(
                    painter = painterResource(id = R.drawable.icon_google),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(CircleShape)
                        .height(100.dp)
                )

                Column {
                    TextShow("user?.displayName.toString()")
                    TextShow("user?.phoneNumber.toString()")
                    TextShow("user?.email.toString()")
                }
            }
            Button(
                onClick = {

                },
                Modifier
                    .fillMaxWidth(.8f)
                    .align(Alignment.CenterHorizontally)
            ) {
                TextShow("Sign Out")
            }

        }
    }
}
