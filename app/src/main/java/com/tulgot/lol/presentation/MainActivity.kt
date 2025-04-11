package com.tulgot.lol.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.tulgot.lol.presentation.championlistscreen.ChampionListScreen
import com.tulgot.lol.presentation.championdetailscreen.ChampionDetailsScreen
import com.tulgot.lol.ui.theme.LOLTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LOLTheme {

                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = ChampionList) {
                    composable<ChampionList>{
                        ChampionListScreen{name -> navController.navigate(ChampionDetails(name = name)) }
                    }

                    composable<ChampionDetails> {

                        ChampionDetailsScreen()
                    }

                }

            }
        }
    }
}