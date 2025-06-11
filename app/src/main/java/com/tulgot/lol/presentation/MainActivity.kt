package com.tulgot.lol.presentation

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import com.tulgot.lol.App
import com.tulgot.lol.presentation.navigation.LoginNavigation
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