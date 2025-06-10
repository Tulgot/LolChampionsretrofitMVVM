package com.tulgot.lol.presentation

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.navigation
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.tulgot.lol.App
import com.tulgot.lol.modules.login.presentation.ui.LoginScreen
import com.tulgot.lol.modules.login.presentation.ui.ProfileScreen
import com.tulgot.lol.modules.login.presentation.ui.RegistrationScreen
import com.tulgot.lol.presentation.bookmarkdetailscreen.BookMarkDetailScreen
import com.tulgot.lol.presentation.bookmarksscreen.BookMarksScreen
import com.tulgot.lol.presentation.championdetailscreen.ChampionDetailsScreen
import com.tulgot.lol.presentation.championlistscreen.ChampionListScreen
import com.tulgot.lol.presentation.mapscreen.MapScreen
import com.tulgot.lol.presentation.navigation.LoginNavigation
import com.tulgot.lol.presentation.settingscreen.SettingsScreen
import com.tulgot.lol.ui.theme.LOLTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        App.appContext = this
        enableEdgeToEdge()
        setContent {
            LOLTheme {
                LoginNavigation()

            }
        }
    }
}