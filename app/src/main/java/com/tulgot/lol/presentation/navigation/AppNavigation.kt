package com.tulgot.lol.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.tulgot.lol.domain.BottomNavigationItem
import com.tulgot.lol.modules.login.presentation.ui.ProfileScreen
import com.tulgot.lol.presentation.BookMarksDetailRoute
import com.tulgot.lol.presentation.BookMarksRoute
import com.tulgot.lol.presentation.ChampionDetailsRoute
import com.tulgot.lol.presentation.ChampionListRoute
import com.tulgot.lol.presentation.GoogleMapRoute
import com.tulgot.lol.presentation.HomeGraph
import com.tulgot.lol.presentation.LoginRoute
import com.tulgot.lol.presentation.ProfileRoute
import com.tulgot.lol.presentation.SettingsRoute
import com.tulgot.lol.presentation.bookmarkdetailscreen.BookMarkDetailScreen
import com.tulgot.lol.presentation.bookmarksscreen.BookMarksScreen
import com.tulgot.lol.presentation.championdetailscreen.ChampionDetailsScreen
import com.tulgot.lol.presentation.championlistscreen.ChampionListScreen
import com.tulgot.lol.presentation.listRoute
import com.tulgot.lol.presentation.mapscreen.MapScreen
import com.tulgot.lol.presentation.settingscreen.SettingsScreen


@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun AppNavigation() {

    val items = listOf(
        BottomNavigationItem(
            title = "Champion List",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home
        ),
        BottomNavigationItem(
            title = "BookMarked",
            selectedIcon = Icons.Filled.Favorite,
            unselectedIcon = Icons.Outlined.FavoriteBorder
        ),
        BottomNavigationItem(
            title = "Settings",
            selectedIcon = Icons.Filled.Settings,
            unselectedIcon = Icons.Outlined.Settings
        ),
        BottomNavigationItem(
            title = "Profile",
            selectedIcon = Icons.Filled.Person,
            unselectedIcon = Icons.Outlined.Person
        ),
        BottomNavigationItem(
            title = "Map",
            selectedIcon = Icons.Filled.LocationOn,
            unselectedIcon = Icons.Outlined.LocationOn
        )
    )

    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }
    val navController = rememberNavController()

    navController.addOnDestinationChangedListener { controller, destination, arguments ->
        selectedItemIndex = listRoute.indexOfFirst {
            it.toString().substringAfterLast('.')
                .substringBefore('@') == destination.route.toString()
                .substringAfterLast(".")
        }
    }


    Scaffold(
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedItemIndex == index,
                        onClick = {
                            navController.navigate(listRoute[index])
                        },
                        label = {
                            Text(text = item.title)
                        },
                        icon = {
                            Icon(
                                imageVector = if (index == selectedItemIndex) {
                                    item.selectedIcon
                                } else item.unselectedIcon,
                                contentDescription = item.title
                            )
                        }
                    )

                }
            }
        }
    ) { innerPadding ->
        Box(
            Modifier
                .fillMaxSize()
                .padding(bottom = innerPadding.calculateBottomPadding()),
            contentAlignment = Alignment.Center
        ) {

            Column {
                NavHost(navController = navController, startDestination = HomeGraph) {
                    navigation<HomeGraph>(startDestination = ChampionListRoute) {
                        composable<ChampionListRoute> {
                            ChampionListScreen(
                                navigateToDetail = { name ->
                                    navController.navigate(ChampionDetailsRoute(name = name)) {
                                    }
                                }
                            )
                        }
                        composable<ChampionDetailsRoute> {
                            ChampionDetailsScreen()
                        }
                        composable<SettingsRoute> {
                            SettingsScreen(
                                navigateToChampionList = {
                                    navController.navigate(
                                        ChampionListRoute
                                    )
                                },
                                navigateToBookMarks = { navController.navigate(BookMarksRoute) }
                            )
                        }

                        composable<BookMarksRoute> {
                            BookMarksScreen(
                                navigateToBookMarsDetail = {
                                    navController.navigate(BookMarksDetailRoute(id = it))
                                }
                            )
                        }
                        composable<BookMarksDetailRoute> {
                            BookMarkDetailScreen()
                        }
                        composable<ProfileRoute> {
                            LaunchedEffect(Unit) {
                                if (FirebaseAuth.getInstance().currentUser == null) {
                                    navController.navigate(LoginRoute){
                                        popUpTo(route = HomeGraph) { inclusive = true }
                                    }
                                }

                            }
                            ProfileScreen(
                                navigateToLogIn = {
                                    navController.navigate(LoginRoute) {
                                        popUpTo(route = HomeGraph) { inclusive = true }
                                    }
                                }
                            )
                        }
                        composable<GoogleMapRoute> {
                            MapScreen()
                        }
                    }

                }


            }
        }
    }

}