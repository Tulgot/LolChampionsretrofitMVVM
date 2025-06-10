package com.tulgot.lol.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.tulgot.lol.modules.login.presentation.ui.LoginScreen
import com.tulgot.lol.modules.login.presentation.ui.RegistrationScreen
import com.tulgot.lol.presentation.HomeGraph
import com.tulgot.lol.presentation.LoginRoute
import com.tulgot.lol.presentation.RegistrationRoute
import com.tulgot.lol.presentation.SignInGraph

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun LoginNavigation(){
    val navController = rememberNavController()
    NavHost(navController, startDestination = SignInGraph){
        navigation<SignInGraph>(startDestination = LoginRoute){
            composable<LoginRoute> {
                LaunchedEffect(Unit) {
                    if (FirebaseAuth.getInstance().currentUser != null)
                        navController.navigate(HomeGraph){
                            popUpTo(route = SignInGraph) { inclusive = true }
                        }
                }
                LoginScreen(
                    navigateToChampionList = {
                        navController.navigate(HomeGraph){
                            popUpTo(route = SignInGraph) { inclusive = true }
                        }
                    },
                    navigateToRegistration = {
                        navController.navigate(RegistrationRoute)
                    }
                )
            }
            composable<RegistrationRoute> {
                RegistrationScreen(
                    navToLogin = { navController.navigate(LoginRoute)},
                    navToChampionList = {
                        navController.navigate(HomeGraph){
                            popUpTo(route = SignInGraph) { inclusive = true }
                        }

                    }
                )
            }
            composable<HomeGraph> {
                AppNavigation()
            }
        }
    }
}