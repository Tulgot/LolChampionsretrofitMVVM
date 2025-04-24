package com.tulgot.lol.presentation

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tulgot.lol.presentation.bookmarkdetailscreen.BookMarkDetailScreen
import com.tulgot.lol.presentation.bookmarksscreen.BookMarksScreen
import com.tulgot.lol.presentation.championdetailscreen.ChampionDetailsScreen
import com.tulgot.lol.presentation.championlistscreen.ChampionListScreen
import com.tulgot.lol.presentation.settingscreen.SettingsScreen
import com.tulgot.lol.ui.theme.LOLTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LOLTheme {

                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = ChampionList) {
                    composable<ChampionList> {
                        ChampionListScreen(
                            navigateToDetail = { name ->
                                navController.navigate( ChampionDetails( name = name ) )
                                               },
                            navigateToSettings = { navController.navigate(Settings) },
                            navigateToBookMarks = { navController.navigate(BookMarks) }
                        )
                    }

                    composable<ChampionDetails> {
                        ChampionDetailsScreen(
                            navigateToSettings = { navController.navigate(Settings) },
                            navigateToBooKMarks = { navController.navigate(BookMarks) },
                            navigateToChampionList = { navController.navigate(ChampionList) }
                        )
                    }
                        composable<Settings> {
                            SettingsScreen(
                                navigateToChampionList = { navController.navigate(ChampionList) },
                                navigateToBookMarks = { navController.navigate(BookMarks) }
                            )
                        }

                        composable<BookMarks> {
                            BookMarksScreen(
                                navigateToChampionList = { navController.navigate(ChampionList) },
                                navigateToSettings = { navController.navigate(Settings) },
                                navigateToBookMarsDetail = {
                                    navController.navigate(BookMarksDetail(id = it))
                                }
                            )
                        }
                    composable<BookMarksDetail> {
                            BookMarkDetailScreen(
                                navigateToChampionList = { navController.navigate(ChampionList) },
                                navigateToSettings = { navController.navigate(Settings) },
                                navigateToBookMarks = { navController.navigate(BookMarks)}
                            )
                        }

                    }

                }

            }
        }
    }
